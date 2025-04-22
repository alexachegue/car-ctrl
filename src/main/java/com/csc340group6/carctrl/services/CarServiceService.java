package com.csc340group6.carctrl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceService {

    @Autowired
    private CarServiceRepository carServiceRepository;

    public List<CarService> getAllCarServices() {
        return carServiceRepository.findAll();
    }

    public List<CarService> getCarServicesByCategory(CarService.ServiceCategory category) {
        return carServiceRepository.findByCategory(category);
    }

    public void addCarService(CarService service) {
        carServiceRepository.save(service);
    }
}
