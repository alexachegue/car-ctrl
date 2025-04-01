package com.csc340group6.carctrl.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceService {

    @Autowired
    public ServiceRepository serviceRepository;

    public List<com.csc340group6.carctrl.services.Service> getAllServices(){
        return serviceRepository.findAll();
    }

    public com.csc340group6.carctrl.services.Service getServiceById(int serviceId) {
        return serviceRepository.findById(serviceId).orElse(null);
    }

}
