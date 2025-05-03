package com.csc340group6.carctrl.alerts;

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
    public Alert createAlert(int providerId, int userId, int appointmentId, Alert.AlertType alertType, String message) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        Provider provider = providerRepository.findById(providerId)
                .orElseThrow(() -> new RuntimeException("Provider not found"));

        Alert alert = new Alert();
        alert.setUser(user);
        alert.setAppointment(appointment);
        alert.setAlertType(alertType);
        alert.setMessage(message);
        alert.setDismissible(true);
        alert.setProvider(provider);

        return alertRepository.save(alert);
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
