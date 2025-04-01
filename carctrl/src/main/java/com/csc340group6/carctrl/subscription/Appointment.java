package com.csc340group6.carctrl.subscription;

import com.csc340group6.carctrl.car.Car;
import com.csc340group6.carctrl.services.Service;
import com.csc340group6.carctrl.user.User;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appointmentId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date appointmentDate;

    private String status;

    @Column(name = "created_at", updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    public Appointment(){}

    public Appointment(User user, Service service, Car car, String description, String status, Date appointmentDate){
        this.user = user;
        this.service = service;
        this.car = car;
        this.description = description;
        this.status = status;
        this.appointmentDate = appointmentDate;
    }

    public int getAppointmentId() { return appointmentId; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public Service getService() { return service; }

    public void setService(Service service) { this.service = service; }

    public Car getCar() { return car; }

    public void setCar(Car car) { this.car = car; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public Date getAppointmentDate() { return appointmentDate; }

    public void setAppointmentDate(Date appointmentDate) { this.appointmentDate = appointmentDate; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public Timestamp getCreatedAt() { return createdAt; }

    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
