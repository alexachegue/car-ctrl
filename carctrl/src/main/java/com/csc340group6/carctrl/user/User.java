package com.csc340group6.carctrl.user;

import com.csc340group6.carctrl.car.Car;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String username;
    private String password;
    private String email;
    private String phoneNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateJoined;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Car> cars;

    public User(int userId, String username, String password, String email, String phoneNumber, Date dateJoined){
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateJoined = dateJoined;
    }

    public User(int userId, String username, String password, String email, String phoneNumber, Date dateJoined, List<Car> cars){
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateJoined = dateJoined;
        this.cars = cars;
    }


    public User(String username, String password, String email, String phoneNumber, Date dateJoined){
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateJoined = dateJoined;

    }

    public User(){}

    public int getUserId(){
        return userId;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phone_number){
        this.phoneNumber = phone_number;
    }

    public void setDateJoined(Date date){
        this.dateJoined = date;
    }

    public Date getDateJoined(){
        return this.dateJoined;
    }

    /**
    public Car getCar(){
        return this.car;
    }

    public void setCar(Car car){
        this.car = car;
    }
     **/

    @Override
    public String toString(){
        return "User{" + "userid= " + userId +
                ", username= " + username +
                ", email= " + email +
                ", phone number= " + phoneNumber +
                ", date joined= " + dateJoined +
                ", car= "  +
                '}';
    }

}
