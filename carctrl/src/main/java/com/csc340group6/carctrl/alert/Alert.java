package com.csc340group6.carctrl.alert;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "alerts")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int alertId;  // Primary key

    @Column(name = "provider_id")
    private int providerId;  // Provider who will receive the alert

    @Column(name = "user_id")
    private int userId;  // User who booked the appointment

    @Column(name = "appointment_id")
    private int appointmentId;  // The ID of the appointment associated with this alert

    @Column(name = "alert_type")
    private String alertType;  // Type of the alert (e.g., "Appointment Created", "Appointment Updated")

    @Column(name = "message")
    private String message;  // Message content

    @Column(name = "created_at")
    private Date createdAt;  // Date when the alert was created (without time)

    public Alert() {}

    public Alert(int providerId, int userId, int appointmentId, String alertType, String message) {
        this.providerId = providerId;
        this.userId = userId;
        this.appointmentId = appointmentId;
        this.alertType = alertType;
        this.message = message;
        this.createdAt = new Date(System.currentTimeMillis());  // Set the current date (without time)
    }

    // Getters and Setters
    public int getAlertId() {
        return alertId;
    }

    public void setAlertId(int alertId) {
        this.alertId = alertId;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getAlertType() {
        return alertType;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}

