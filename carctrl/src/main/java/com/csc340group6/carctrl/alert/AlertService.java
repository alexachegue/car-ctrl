package com.csc340group6.carctrl.alert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    // Create an alert for a provider
    public Alert createAlert(int providerId, int userId, int appointmentId, String alertType, String message) {
        Alert alert = new Alert(providerId, userId, appointmentId, alertType, message);
        return alertRepository.save(alert);
    }

    // Get all alerts for a specific provider
    public List<Alert> getAlertsByProviderId(int providerId) {
        return alertRepository.findByProviderId(providerId);
    }

    // Get all alerts for a specific provider and alert type
    public List<Alert> getAlertsByProviderIdAndAlertType(int providerId, String alertType) {
        return alertRepository.findByProviderIdAndAlertType(providerId, alertType);
    }

    // Get all alerts for a specific appointment
    public List<Alert> getAlertsByAppointmentId(int appointmentId) {
        return alertRepository.findByAppointmentId(appointmentId);
    }
}

