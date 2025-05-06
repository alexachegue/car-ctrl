package com.csc340group6.carctrl.provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    public List<Provider> getAllProviders() {
        return providerRepository.findAll();
    }

    public Provider getProviderById(int providerId) {
        return providerRepository.findById(providerId).orElse(null);
    }

    public Provider getByProvidername(String providername) {
        return providerRepository.getProviderByProvidername(providername);
    }

    public void addNewProvider(Provider provider) {
        providerRepository.save(provider);
    }

    // Update Provider information
    public void updateProvider(int providerId, Provider updatedProvider) {
        Provider existing = getProviderById(providerId);
        if (existing != null) {
            existing.setProvidername(updatedProvider.getProvidername());
            existing.setEmail(updatedProvider.getEmail());
            existing.setPhone(updatedProvider.getPhone());
            existing.setAddress(updatedProvider.getAddress());
            existing.setOperatingHours(updatedProvider.getOperatingHours());
            existing.setBio(updatedProvider.getBio());
            existing.setStatus(updatedProvider.getStatus());
            existing.setActiveDate(updatedProvider.getActiveDate());

            providerRepository.save(existing);
        }
    }

    public void deleteProviderById(int providerId) {
        providerRepository.deleteById(providerId);
    }

    public List<Provider> getProvidersByStatus(Provider.ProviderStatus status) {
        return providerRepository.findByStatus(status);
    }

    public List<Provider> getProvidersByService(int serviceId) {
        return providerRepository.findByServices_ServiceId(serviceId);
    }

}
