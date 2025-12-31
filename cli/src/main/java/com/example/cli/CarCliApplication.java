package com.example.cli;

import java.net.http.HttpClient;

public class CarCliApplication {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("No Command Provided");
            return;
        }

        try {
            HttpClient client = HttpClient.newHttpClient();
            String command = args[0];

            switch (command) {
                case "create-car" -> CliUtils.createCar(client, args);
                case "add-fuel" -> CliUtils.addFuel(client, args);
                case "fuel-stats" -> CliUtils.getFuelStats(client, args);
                default -> System.err.println("Unknown Command: " + command);
            }
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
