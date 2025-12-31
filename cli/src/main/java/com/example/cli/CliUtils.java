package com.example.cli;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CliUtils {

    private static final String BASE_URL = "http://localhost:8080";

    // ---------------- CREATE CAR ----------------
    public static void createCar(HttpClient client, String[] args) throws Exception {

        String brand = getArg(args, "--brand");
        String model = getArg(args, "--model");
        String year = getArg(args, "--year");

        String json = String.format(
                "{\"brand\":\"%s\",\"model\":\"%s\",\"year\":%s}",
                brand, model, year
        );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/api/cars"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }

    // ---------------- ADD FUEL ----------------
    public static void addFuel(HttpClient client, String[] args) throws Exception {

        String carId = getArg(args, "--carId");
        String liters = getArg(args, "--liters");
        String price = getArg(args, "--price");
        String odometer = getArg(args, "--odometer");

        String json = String.format(
                "{\"liters\":%s,\"price\":%s,\"odometer\":%s}",
                liters, price, odometer
        );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/api/cars/" + carId + "/fuel"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Fuel entry added");
    }

    // ---------------- FUEL STATS ----------------
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

        // Simple output formatting (no JSON libs)
        String body = response.body();

        System.out.println("Fuel statistics:");
        System.out.println(body);
    }

    // ---------------- ARG PARSER ----------------
    private static String getArg(String[] args, String key) {
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals(key)) {
                return args[i + 1];
            }
        }
        throw new IllegalArgumentException("Missing argument: " + key);
    }
}
