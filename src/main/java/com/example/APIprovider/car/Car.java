package com.example.APIprovider.car;

//import com.csc340group6.carctrl.user.User;

import com.example.APIprovider.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private int carId;

    private String make;
    private String model;
    private int year;
    private String color;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Car(){}

    public Car(User user, String make, String model, int year, String color){
        this.user = user;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
    }

    public int getCarId(){
        return carId;
    }

    public void setCarId(int carId){
        this.carId = carId;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public String getMake(){
        return make;
    }

    public void setMake(String make){
        this.make = make;
    }

    public String getModel(){
        return model;
    }

    public void setModel(String model){
        this.model = model;
    }

    public int getYear(){
        return year;
    }

    public void setYear(int year){
        this.year = year;
    }

    public String getColor(){
        return color;
    }

    public void setColor(String color){
        this.color = color;
    }
}
