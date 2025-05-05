package com.example.APIprovider.alert;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Integer> {

    List<Alert> findByAlertType(String alertType);

    // Get all alerts for a specific provider
    List<Alert> findByProviderId(int providerId);

    // Get all alerts for a specific provider and alert type
    List<Alert> findByProviderIdAndAlertType(int providerId, String alertType);

    // Get all alerts for a specific appointment
    List<Alert> findByAppointmentId(int appointmentId);
}