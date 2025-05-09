package com.csc340group6.carctrl.services;

import com.csc340group6.carctrl.provider.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface CarServiceRepository extends JpaRepository<CarService, Integer>{

    @Query("SELECT s FROM CarService s WHERE s.category = :category")
    List<CarService> findByCategory(@Param("category") CarService.ServiceCategory category);

    List<CarService> findByProviderIsNull();
    List<CarService> findByProvider_ProviderId(int providerId);

    List<CarService> findByProvider(Provider provider);
}
