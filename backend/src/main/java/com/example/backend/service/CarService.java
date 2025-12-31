package com.example.backend.service;

import com.example.backend.exception.CarNotFoundException;
import com.example.backend.model.Car;
import com.example.backend.model.FuelEntry;
import com.example.backend.model.FuelStats;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CarService {

    private final Map<Long, Car> cars = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Car createCar(String brand, String model, int year) {
        Long id = idGenerator.getAndIncrement();
        Car car = new Car(id, brand, model, year);
        cars.put(id, car);
        return car;
    }

    public List<Car> getAllCars() {
        return new ArrayList<>(cars.values());
    }

    public void addFuel(Long carId, FuelEntry fuelEntry) {
        Car car = getCarById(carId);
        car.getFuelEntries().add(fuelEntry);
    }

    public FuelStats getFuelStats(Long carId) {
        Car car = getCarById(carId);
        List<FuelEntry> entries = new ArrayList<>(car.getFuelEntries());
        entries.sort(Comparator.comparingInt(FuelEntry::getOdometer));

        if (entries.isEmpty()) {
            return new FuelStats(0, 0, 0);
        }

        double totalFuel = entries.stream()
                .mapToDouble(FuelEntry::getLiters)
                .sum();

        double totalCost = entries.stream()
                .mapToDouble(entry -> entry.getLiters() * entry.getPrice())
                .sum();

        int firstOdometer = entries.get(0).getOdometer();
        int lastOdometer = entries.get(entries.size() - 1).getOdometer();
        int distance = lastOdometer - firstOdometer;
        if (distance < 0) {
            throw new IllegalStateException("Odometer readings are inconsistent, resulting in negative distance.");
        }

        double avgConsumption = distance > 0
                ? (totalFuel / distance) * 100
                : 0;

        return new FuelStats(totalFuel, totalCost, avgConsumption);
    }

    private Car getCarById(Long carId) {
        Car car = cars.get(carId);
        if (car == null) {
            throw new CarNotFoundException(carId);
        }
        return car;
    }
}
