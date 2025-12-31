package com.example.backend.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Car {

    private final Long id;
    private final String brand;
    private final String model;
    private final int year;
    private final List<FuelEntry> fuelEntries = new CopyOnWriteArrayList<>();

    public Car(Long id, String brand, String model, int year) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public List<FuelEntry> getFuelEntries() {
        return fuelEntries;
    }
}
