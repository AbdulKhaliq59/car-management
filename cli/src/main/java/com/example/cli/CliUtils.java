package com.example.cli;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class CliUtils {

    private static final String BASE_URL = System.getenv().getOrDefault("CAR_API_BASE_URL", "http://localhost:8080");
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private CliUtils() {
        // Prevent instantiation
    }

    // Create Car   
    public static void createCar(HttpClient client, String[] args) throws Exception {

        String brand = getArg(args, "--brand");
        String model = getArg(args, "--model");
        String year = getArg(args, "--year");

        Map<String, Object> carData = new HashMap<>();
        carData.put("brand", brand);
        carData.put("model", model);
        carData.put("year", Integer.parseInt(year));
        
        String json = objectMapper.writeValueAsString(carData);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/api/cars"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 201) {
            System.out.println("Car created successfully:");
            System.out.println(response.body());
        } else {
            System.err.println("Error creating car: " + response.body());
        }
    }

    // Add Fuel Entry
    public static void addFuel(HttpClient client, String[] args) throws Exception {

        String carId = getArg(args, "--carId");
        String liters = getArg(args, "--liters");
        String price = getArg(args, "--price");
        String odometer = getArg(args, "--odometer");

        Map<String, Object> fuelData = new HashMap<>();
        fuelData.put("liters", Double.parseDouble(liters));
        fuelData.put("price", Double.parseDouble(price));
        fuelData.put("odometer", Integer.parseInt(odometer));
        
        String json = objectMapper.writeValueAsString(fuelData);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/api/cars/" + carId + "/fuel"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            System.err.println("Error adding fuel: " + response.body());
            return;
        }
        System.out.println("Fuel entry added");
    }

    // Get Fuel Stats
    public static void getFuelStats(HttpClient client, String[] args) throws Exception {

        String carId = getArg(args, "--carId");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/api/cars/" + carId + "/fuel/stats"))
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            System.out.println(response.body());
            return;
        }

        String body = response.body();

        System.out.println("Fuel statistics:");
        System.out.println(body);
    }

    // List Cars
    public static void listCars(HttpClient client, String[] args) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/api/cars"))
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            System.err.println("Error listing cars: " + response.body());
            return;
        }

        System.out.println("Cars:");
        System.out.println(response.body());
    }

    // args helper
    private static String getArg(String[] args, String key) {
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals(key)) {
                String value = args[i + 1];
                if (value.startsWith("--")) {
                    throw new IllegalArgumentException("Missing value for argument: " + key);
                }
                return value;
            }
        }
        throw new IllegalArgumentException("Missing argument: " + key);
    }
}
