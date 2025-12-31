package com.example.cli;

import java.net.http.HttpClient;

public class CarCliApplication {
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("No Command Provided");
            return;
        }

        HttpClient client = HttpClient.newHttpClient();
        String command = args[0];

        switch (command) {
            case "create-car" -> CliUtils.createCar(client, args);
            case "add-fuel" -> CliUtils.addFuel(client, args);
            case "fuel-stats" -> CliUtils.getFuelStats(client, args);
            default -> System.out.println("Unknown Command: " + command);
        }
    }
}
