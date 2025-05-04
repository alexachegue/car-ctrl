package com.csc340group6.carctrl.alert;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Integer> {
    List<Alert> findByUser_UserIdOrderByCreatedAtDesc(int userId);

    List<Alert> findByProvider_ProviderId(int providerId);
    List<Alert> findByProvider_ProviderIdAndAlertType(int providerId, Alert.AlertType alertType);

    List<Alert> findByAppointment_AppointmentId(int appointmentId);
}
