package com.example.APIprovider.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarServiceRepository extends JpaRepository<CarService, Integer>{
    List<CarService> findByProviderIsNull();
    List<CarService> findByProvider_ProviderId(int providerId);

}