package com.example.backend.model;


public class FuelEntry {
    private final double liters;
    private final double price;
    private final int odometer;

    public FuelEntry(double liters, double price, int odometer) {
        this.liters = liters;
        this.price = price;
        this.odometer = odometer;
    }

    public double getLiters() {
        return liters;
    }

    public double getPrice() {
        return price;
    }

    public int getOdometer() {
        return odometer;
    }
}