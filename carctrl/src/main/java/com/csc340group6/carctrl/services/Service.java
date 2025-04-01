package com.csc340group6.carctrl.services;


import com.csc340group6.carctrl.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name = "services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int serviceId;

    @Column(nullable = false)
    private String category;

    private String name;
    private String description;

    public Service(){}

    public Service(int serviceId, String category, String name, String description){
        this.serviceId = serviceId;
        this.category = category;
        this.name= name;
        this.description= description;
    }

    public int getServiceId(){
        return serviceId;
    }

    public void setServiceId(int serviceId){
        this.serviceId= serviceId;
    }

    public String getCategory(){
        return category;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }
}
