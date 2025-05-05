package com.example.APIprovider.services;

import com.example.APIprovider.provider.Provider;
import jakarta.persistence.*;

@Entity
@Table(name = "services")
public class CarService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int serviceId;

    @Column(nullable = false)
    private String category;

    private String name;
    private String description;


    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;


    public CarService(){}

    public CarService(int serviceId, String category, String name, String description){
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

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

}