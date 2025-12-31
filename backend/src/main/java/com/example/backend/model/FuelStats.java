package com.example.backend.model;

public class FuelStats {
    private double totalFuel;
    private double totalCost;
    private double averageConsumption;

    public FuelStats(double totalFuel, double totalCost, double averageConsumption) {
        this.totalFuel = totalFuel;
        this.totalCost = totalCost;
        this.averageConsumption = averageConsumption;
    }

    public double getTotalFuel() {
        return totalFuel;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public double getAverageConsumption() {
        return averageConsumption;
    }
}