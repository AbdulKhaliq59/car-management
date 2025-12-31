package com.example.backend.dto;

public class AddFuelRequest {
    private double liters;
    private double price;
    private int odometer;

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