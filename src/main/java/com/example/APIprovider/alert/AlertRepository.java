package com.example.APIprovider.alert;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Integer> {
    List<Alert> findByUser_UserIdOrderByCreatedAtDesc(int userId);

    List<Alert> findByAlertType(String alertType);

    List<Alert> findByProvider_ProviderId(int providerId);

    List<Alert> findByProviderProviderIdAndAlertType(int providerId, Alert.AlertType alertType);

    List<Alert> findByAppointment_AppointmentId(int appointmentId);
}