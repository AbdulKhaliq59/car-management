package com.example.backend.controller;

import com.example.backend.dto.AddFuelRequest;
import com.example.backend.dto.CreateCarRequest;
import com.example.backend.model.Car;
import com.example.backend.model.FuelEntry;
import com.example.backend.model.FuelStats;
import com.example.backend.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    // Create Car
    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody CreateCarRequest request) {
        Car car = carService.createCar(
                request.getBrand(),
                request.getModel(),
                request.getYear()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(car);
    }

    // List Cars
    @GetMapping
    public List<Car> getCars() {
        return carService.getAllCars();
    }

    // Add Fuel
    @PostMapping("/{id}/fuel")
    public ResponseEntity<Void> addFuel(
            @PathVariable Long id,
            @RequestBody AddFuelRequest request
    ) {
        carService.addFuel(
                id,
                new FuelEntry(
                        request.getLiters(),
                        request.getPrice(),
                        request.getOdometer()
                )
        );
        return ResponseEntity.ok().build();
    }

    // Get Fuel Stats
    @GetMapping("/{id}/fuel/stats")
    public FuelStats getFuelStats(@PathVariable Long id) {
        return carService.getFuelStats(id);
    }
}
