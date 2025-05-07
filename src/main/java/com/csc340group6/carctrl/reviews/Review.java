package com.csc340group6.carctrl.reviews;

import com.csc340group6.carctrl.provider.Provider;
import com.csc340group6.carctrl.reply.Reply;
import com.csc340group6.carctrl.subscription.Appointment;
import com.csc340group6.carctrl.user.User;
import jakarta.persistence.*;

import java.sql.Timestamp;


@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "review_id", nullable = false)
    private int reviewId;

    @ManyToOne
    @JoinColumn(name = "appointment_id", nullable = false)
    private Appointment appointment;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    private Provider provider;

    @OneToOne(mappedBy = "review", cascade = CascadeType.ALL)
    private Reply reply;

    private int rating;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String providerResponse;

    @Column(name = "created_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    public Review() {}

    public Review(int reviewId, Appointment appointment, User user, Provider provider, int rating, String description) {
        this.appointment = appointment;
        this.user = user;
        this.provider = provider;
        this.rating = rating;
        this.description = description;
    }

    public int getReviewId() {
        return reviewId; }

    public Appointment getAppointment() {
        return appointment; }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment; }

    public User getUser() {
        return user; }

    public void setUser(User user) {
        this.user = user; }

    public Provider getProvider() {
        return provider; }

    public void setProvider(Provider provider) {
        this.provider = provider; }

    public int getRating() {
        return rating; }

    public void setRating(int rating) {
        this.rating = rating; }

    public String getDescription() {
        return description; }

    public void setDescription(String description) {
        this.description = description; }

    public String getProviderResponse() {
        return providerResponse; }

    public void setProviderResponse(String providerResponse) {
        this.providerResponse = providerResponse; }

    public Timestamp getCreatedAt() {
        return createdAt; }

    public void setCreatedAt(Timestamp time) {
        this.createdAt = time;
    }

    public Reply getReply() {
        return reply;
    }

    public void setReply(Reply reply) {
        this.reply = reply;
    }


}