package com.example.backend.servlet;

import com.example.backend.exception.CarNotFoundException;
import com.example.backend.model.FuelStats;
import com.example.backend.service.CarService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class FuelStatsServlet extends HttpServlet {

    private final CarService carService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public FuelStatsServlet(CarService carService) {
        this.carService = carService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("application/json");

        String carIdParam = req.getParameter("carId");

        // 1️⃣ Manual query param validation
        if (carIdParam == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\":\"Missing carId parameter\"}");
            return;
        }

        try {
            Long carId = Long.parseLong(carIdParam);

            FuelStats stats = carService.getFuelStats(carId);

            // 2️⃣ Explicit success response
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write(objectMapper.writeValueAsString(stats));

        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\":\"Invalid carId format\"}");

        } catch (CarNotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}
