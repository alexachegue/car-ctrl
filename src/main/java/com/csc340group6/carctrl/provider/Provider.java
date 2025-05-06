package com.csc340group6.carctrl.provider;

import com.csc340group6.carctrl.services.CarService;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "providers")
public class Provider {

    public enum ProviderStatus {
        ACTIVE,
        BUSY,
        OFFLINE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int providerId;

    @Column(nullable = false)
    private String providername;

    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    private String address;

    @Column(name = "operating_hours")
    private String operatingHours;

    private String bio;

    @Enumerated(EnumType.STRING)
    private ProviderStatus status;

    @Column(name = "active_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date activeDate;

    @ManyToMany
    @JoinTable(
            name = "provider_services",
            joinColumns = @JoinColumn(name = "provider_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private List<CarService> services;

    // Constructors
    public Provider() {}

    public Provider(int providerId, String providername, String password, String email, String phone, String address,
                    String operatingHours, String bio, ProviderStatus status, Date activeDate) {
        this.providerId = providerId;
        this.providername = providername;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.operatingHours = operatingHours;
        this.bio = bio;
        this.status = status;
        this.activeDate = activeDate;
    }

    public Provider(String providername, String password, String email, String phone, String address,
                    String operatingHours, String bio, ProviderStatus status, Date activeDate) {
        this.providername = providername;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.operatingHours = operatingHours;
        this.bio = bio;
        this.status = status;
        this.activeDate = activeDate;
    }

    // Getters and Setters
    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public String getProvidername() {
        return providername;
    }

    public void setProvidername(String providername) {
        this.providername = providername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOperatingHours() {
        return operatingHours;
    }

    public void setOperatingHours(String operatingHours) {
        this.operatingHours = operatingHours;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public ProviderStatus getStatus() {
        return status;
    }

    public void setStatus(ProviderStatus status) {
        this.status = status;
    }

    public Date getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(Date activeDate) {
        this.activeDate = activeDate;
    }

    public List<CarService> getServices() {
        return services;
    }

    public void setServices(List<CarService> services) {
        this.services = services;
    }

}
