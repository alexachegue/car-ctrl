package com.csc340group6.carctrl.services;

import com.csc340group6.carctrl.provider.Provider;
import jakarta.persistence.*;

@Entity(name="CarService")
@Table(name = "services")
public class CarService {

    public enum ServiceCategory {
        MAINTENANCE,
        REPAIR,
        MODIFICATION
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int serviceId;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private ServiceCategory category;

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

    public CarService() {}

    public CarService(int serviceId, ServiceCategory category, String name, String description) {
        this.serviceId = serviceId;
        this.category = category;
        this.name = name;
        this.description = description;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public ServiceCategory getServiceCategory() {
        return category;
    }

    public void setServiceCategory(ServiceCategory category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

}
