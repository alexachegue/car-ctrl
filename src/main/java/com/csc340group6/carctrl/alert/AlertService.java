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

    public List<Alert> getAlertsByProviderId(int providerId) {
        return alertRepository.findByProvider_ProviderId(providerId);
    }

    public List<Alert> getAlertsByProviderIdAndAlertType(int providerId, Alert.AlertType alertType) {
        return alertRepository.findByProvider_ProviderIdAndAlertType(providerId, alertType);
    }

    public List<Alert> getAlertsByAppointmentId(int appointmentId) {
        return alertRepository.findByAppointment_AppointmentId(appointmentId);
    }
}
