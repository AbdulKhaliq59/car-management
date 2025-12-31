package com.example.backend.dto;

public class CreateCarRequest {
    private String brand;
    private String model;
    private int year;

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }
}