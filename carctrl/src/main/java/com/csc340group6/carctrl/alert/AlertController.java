package com.csc340group6.carctrl.alert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alerts")
public class AlertController {

    @Autowired
    private AlertService alertService;

    // Endpoint to create a new alert
    @PostMapping("/create")
    public ResponseEntity<Alert> createAlert(@RequestBody Alert alert) {
        Alert newAlert = alertService.createAlert(
                alert.getProviderId(),
                alert.getUserId(),
                alert.getAppointmentId(),
                alert.getAlertType(),
                alert.getMessage()
        );
        return ResponseEntity.ok(newAlert);
    }

    // Endpoint to get all alerts for a specific provider
    @GetMapping("/provider/{providerId}")
    public List<Alert> getAlertsByProviderId(@PathVariable int providerId) {
        return alertService.getAlertsByProviderId(providerId);
    }

    // Endpoint to get all alerts for a specific provider and alert type
    @GetMapping("/provider/{providerId}/type/{alertType}")
    public List<Alert> getAlertsByProviderIdAndAlertType(@PathVariable int providerId, @PathVariable String alertType) {
        return alertService.getAlertsByProviderIdAndAlertType(providerId, alertType);
    }

    // Endpoint to get all alerts for a specific appointment
    @GetMapping("/appointment/{appointmentId}")
    public List<Alert> getAlertsByAppointmentId(@PathVariable int appointmentId) {
        return alertService.getAlertsByAppointmentId(appointmentId);
    }
}

