package com.csc340group6.carctrl.user;

import com.csc340group6.carctrl.car.Car;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

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

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateJoined;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Car> cars;

    @Column(name = "profile_picture")
    private String profilePicture;

    public User() {}

    public User(int userId, String username, String password, String email, String phoneNumber, Date dateJoined){
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateJoined = dateJoined;
    }

    public User(int userId, String username, String password, String email, String phoneNumber, Date dateJoined, List<Car> cars, String pfp){
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateJoined = dateJoined;
        this.cars = cars;
        this.profilePicture = pfp;
    }

    public User(String username, String password, String email, String phoneNumber, Date dateJoined){
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateJoined = dateJoined;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phone_number) {
        this.phoneNumber = phone_number;
    }

    public Date getDateJoined() {
        return this.dateJoined;
    }

    public void setDateJoined(Date date) {
        this.dateJoined = date;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public String getProfilePicture(){
        return this.profilePicture;
    }

    public void setProfilePicture(String pfp){
        this.profilePicture = pfp;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid= " + userId +
                ", username= " + username +
                ", email= " + email +
                ", phone number= " + phoneNumber +
                ", date joined= " + dateJoined +
                ", cars= " + (cars != null ? cars.size() : "null") +
                '}';
    }
}