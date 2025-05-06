package com.csc340group6.carctrl.alert;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Integer> {
    List<Alert> findByUser_UserIdOrderByCreatedAtDesc(int userId);

    List<Alert> findByAlertType(String alertType);

    // Get all alerts for a specific provider
    List<Alert> findByProvider_ProviderId(int providerId);

    // Get all alerts for a specific provider and alert type
    List<Alert> findByProviderIdAndAlertType(int providerId, String alertType);

    // Get all alerts for a specific appointment
    List<Alert> findByAppointment_AppointmentId(int appointmentId);
}
