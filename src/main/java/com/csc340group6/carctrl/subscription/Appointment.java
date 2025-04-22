package com.csc340group6.carctrl.subscription;

import com.csc340group6.carctrl.car.Car;
import com.csc340group6.carctrl.services.CarService;
import com.csc340group6.carctrl.user.User;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Timestamp;
import java.util.Date;


@Entity
@Table(name = "appointments")
public class Appointment {

    public enum Status {
        Scheduled,
        Pending,
        Cancelled
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appointmentId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private CarService service;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date appointmentDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "created_at", updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    public Appointment(){}

    public Appointment(User user, CarService service, Car car, String description, Status status, Date appointmentDate){
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

    public CarService getCarService() { return service; }

    public void setCarService(CarService service) { this.service = service; }

    public Car getCar() { return car; }

    public void setCar(Car car) { this.car = car; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public Date getAppointmentDate() { return appointmentDate; }

    public void setAppointmentDate(Date appointmentDate) { this.appointmentDate = appointmentDate; }

    public Status getStatus() { return status; }

    public void setStatus(Status status) { this.status = status; }

    public Timestamp getCreatedAt() { return createdAt; }

    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
