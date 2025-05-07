package com.example.APIprovider.subscription;

import com.example.APIprovider.car.Car;
import com.example.APIprovider.provider.Provider;
import com.example.APIprovider.services.CarService;
import com.example.APIprovider.user.User;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "appointments")
public class Appointment {

    public enum Status {
        Pending,
        Scheduled,
        //Completed,
        Cancelled
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "appointment_id", nullable = false)
    private int appointmentId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private CarService service;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    private String description;

    @JoinColumn(name = "appointment_date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date appointmentDate;

    //private String status;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "created_at", updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    public Appointment() {}

    public Appointment(User user, Provider provider, CarService service, Car car, String description, Status status, Date appointmentDate){
        this.user = user;
        this.provider = provider;
        this.service = service;
        this.car = car;
        this.description = description;
        this.status = status;
        this.appointmentDate = appointmentDate;
    }

//    public int getAppointmentId() { return appointmentId; }
//
//    public User getUser() { return user; }
//    public void setUser(User user) { this.user = user; }
//
//    public Service getService() { return service; }
//    public void setService(Service service) { this.service = service; }
//
//    public Car getCar() { return car; }
//    public void setCar(Car car) { this.car = car; }
//
//    public String getDescription() { return description; }
//    public void setDescription(String description) { this.description = description; }
//
//    public Date getAppointmentDate() { return appointmentDate; }
//    public void setAppointmentDate(Date appointmentDate) { this.appointmentDate = appointmentDate; }
//
//    public String getStatus() { return status; }
//    public void setStatus(String status) { this.status = status; }

    public int getAppointmentId(){
        return appointmentId; }

    public void setAppointmentId(int appointmentId){
        this.appointmentId = appointmentId;
    }

    public User getUser() {
        return user; }

    public void setUser(User user) {
        this.user = user; }

    public int getServiceId(){
        return service.getServiceId();
    }

    public void setServiceId(CarService service){
        this.service = service;
    }

    public Provider getProvider(){
        return provider;
    }

    public String getProviderName() {
        return provider.getProvidername();
    }

    public void setProvider(Provider provider){
        this.provider = provider;
    }

    public CarService getService(){
        return service; }

    public void setService(CarService service){
        this.service = service; }

    public Car getCar() {
        return car; }

    public void setCar(Car car){
        this.car = car; }

    public String getDescription(){
        return description; }

    public void setDescription(String description){
        this.description = description; }

    public Date getAppointmentDate(){
        return appointmentDate; }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate; }

    public Status getStatus(){
        return status; }

    public void setStatus(Status status){
        this.status = status; }

    public Timestamp getCreatedAt(){
        return createdAt; }

    public void setCreatedAt(Timestamp createdAt){
        this.createdAt = createdAt; }
}