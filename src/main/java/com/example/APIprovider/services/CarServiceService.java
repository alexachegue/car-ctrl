package com.example.APIprovider.services;

import com.example.APIprovider.provider.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceService {

    @Autowired
    public CarServiceRepository serviceRepository;

//    public List<com.example.APIprovider.services.Service> getAllServices(){
//        return serviceRepository.findAll();
//    }

//    public CarService getServiceById(int serviceId) {
//        return serviceRepository.findById(serviceId).orElse(null);
//    }

//    public List<Service> getUnassignedServices() {
//        return serviceRepository.findByProviderIsNull();
//    }

//    public List<Service> getServicesByProviderId(int providerId) {
//        return serviceRepository.findByProvider_ProviderId(providerId);
//    }

//    public List<Service> getAllServices() {
//        return serviceRepository.findAll();
//    }
//
//    public List<Service> getUnassignedServices() {
//        return serviceRepository.findByProviderIsNull();
//    }
//
//    public void assignServiceToProvider(int serviceId, int providerId) {
//        Service service = serviceRepository.findById(serviceId).orElse(null);
//        if (service != null) {
//            Provider provider = new Provider();
//            provider.setProviderId(providerId); // Only setting ID to avoid fetching full provider
//            service.setProvider(provider);
//            serviceRepository.save(service);
//        }
//    }

    public List<CarService> getAllServices() {
        return serviceRepository.findAll();
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

    @Autowired
    private com.example.APIprovider.provider.ProviderRepository providerRepository;
}

