package com.csc340group6.carctrl.alert;

import com.csc340group6.carctrl.alert.Alert;
import com.csc340group6.carctrl.alert.AlertRepository;
import com.csc340group6.carctrl.provider.ProviderRepository;
import com.csc340group6.carctrl.subscription.Appointment;
import com.csc340group6.carctrl.subscription.AppointmentRepository;
import com.csc340group6.carctrl.user.User;
import com.csc340group6.carctrl.provider.Provider;
import com.csc340group6.carctrl.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ProviderRepository providerRepository;

    /**
     * Create a new alert.
     */
    public void createAlertForUser(int providerId, int userId, int appointmentId, Alert.AlertType type, String message) {
        Alert alert = new Alert();
        alert.setProvider(providerRepository.findById(providerId).orElse(null));
        alert.setUser(userRepository.findById(userId).orElseThrow());
        alert.setAppointment(appointmentRepository.findById(appointmentId).orElseThrow());
        alert.setAlertType(type);
        alert.setMessage(message);
        alert.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        alertRepository.save(alert);
    }

    //     Get all alerts
    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }
    public List<Alert> getAlertsByType(String type) {
        if (type == null || type.equalsIgnoreCase("All")) {
            return alertRepository.findAll();
        } else {
            return alertRepository.findByAlertType(type);
        }
    }

    public void deleteAlertById(int alertId) {
        alertRepository.deleteById(alertId);
    }


    // Create an alert for a provider
    public Alert createAlert(int providerId, int userId, int appointmentId, Alert.AlertType type, String message) {
        Alert alert = new Alert();
        alert.setProvider(providerRepository.findById(providerId).orElse(null));
        alert.setUser(userRepository.findById(userId).orElseThrow());
        alert.setAppointment(appointmentRepository.findById(appointmentId).orElseThrow());
        alert.setAlertType(type);
        alert.setMessage(message);
        alert.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        alertRepository.save(alert);
        return alert;
    }

    public void createAppointmentConfirmation(User user, Appointment appointment) {
        Alert alert = new Alert(user, null, appointment, Alert.AlertType.success, "Appointment confirmed!");
        alertRepository.save(alert);
    }

    // Get all alerts for a specific provider
    public List<Alert> getAlertsByProviderId(int providerId) {
        return alertRepository.findByProvider_ProviderId(providerId);
    }

    // Get all alerts for a specific provider and alert type
    public List<Alert> getAlertsByProviderIdAndAlertType(int providerId, Alert.AlertType alertType) {
        return alertRepository.findByProviderProviderIdAndAlertType(providerId, alertType);
    }

    // Get all alerts for a specific appointment
    public List<Alert> getAlertsByAppointmentId(int appointmentId) {
        return alertRepository.findByAppointment_AppointmentId(appointmentId);
    }

}