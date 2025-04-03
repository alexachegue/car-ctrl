package com.csc340group6.carctrl.Admin;

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
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Car> carsManaged;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Service> servicesManaged;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointmentsManaged;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> usersManaged;
}


