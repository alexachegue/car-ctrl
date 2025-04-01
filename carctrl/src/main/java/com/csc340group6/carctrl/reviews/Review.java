package com.csc340group6.carctrl.reviews;

import com.csc340group6.carctrl.car.Car;
import com.csc340group6.carctrl.services.Service;
import com.csc340group6.carctrl.subscription.Appointment;
import com.csc340group6.carctrl.user.User;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewId;

    @ManyToOne
    @JoinColumn(name = "appointment_id", nullable = false)
    private Appointment appointment;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private int providerId;

    private int rating;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String providerResponse;

    @Column(name = "created_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    public Review() {}

    public Review(Appointment appointment, User user, int providerId, int rating, String description) {
        this.appointment = appointment;
        this.user = user;
        this.providerId = providerId;
        this.rating = rating;
        this.description = description;
    }

    public int getReviewId() { return reviewId; }
    public Appointment getAppointment() { return appointment; }
    public void setAppointment(Appointment appointment) { this.appointment = appointment; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public int getProviderId() { return providerId; }
    public void setProviderId(int providerId) { this.providerId = providerId; }
    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getProviderResponse() { return providerResponse; }
    public void setProviderResponse(String providerResponse) { this.providerResponse = providerResponse; }
    public Timestamp getCreatedAt() { return createdAt; }
}