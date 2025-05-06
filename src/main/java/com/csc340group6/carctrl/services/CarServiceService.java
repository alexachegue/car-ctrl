package com.csc340group6.carctrl.services;

import com.csc340group6.carctrl.provider.Provider;
import com.csc340group6.carctrl.provider.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CarServiceService {

    @Autowired
    private CarServiceRepository carServiceRepository;

    @Autowired
    private CarServiceRepository serviceRepository;

    @Autowired
    private ProviderRepository providerRepository;

    public List<CarService> getAllCarServices() {
        return carServiceRepository.findAll();
    }

    public List<CarService> getCarServicesByCategory(CarService.ServiceCategory category) {
        return carServiceRepository.findByCategory(category);
    }
    public void addCarService(CarService service) {
        carServiceRepository.save(service);
    }

    public CarService getCarServiceById(int id) {
        return carServiceRepository.findById(id).orElse(null);
    }

    public CarService getServiceById(int serviceId) {
        return serviceRepository.findById(serviceId).orElse(null);
    }

    public List<CarService> getUnassignedServices() {
        return serviceRepository.findByProviderIsNull();
    }

    public List<CarService> getServicesByProviderId(int providerId) {
        return serviceRepository.findByProvider_ProviderId(providerId);
    }

    public void assignServiceToProvider(int serviceId, int providerId) {
        CarService service = getServiceById(serviceId);
        if (service != null) {
            Provider provider = providerRepository.findById(providerId).orElse(null);
            if (provider != null) {
                service.setProvider(provider);
                serviceRepository.save(service);
            }
        }
    }

    public void saveService(CarService service) {
        serviceRepository.save(service);
    }


}
