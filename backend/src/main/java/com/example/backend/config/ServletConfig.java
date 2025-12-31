package com.example.backend.config;

import com.example.backend.service.CarService;
import com.example.backend.servlet.FuelStatsServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {

    @Bean
    public ServletRegistrationBean<FuelStatsServlet> fuelStatsServlet(CarService carService, com.fasterxml.jackson.databind.ObjectMapper objectMapper) {
        return new ServletRegistrationBean<>(
                new FuelStatsServlet(carService, objectMapper),
                "/servlet/fuel-stats"
        );
    }
}
