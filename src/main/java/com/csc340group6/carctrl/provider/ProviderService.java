package com.csc340group6.carctrl.provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    // Fetch all Providers
    public List<Provider> getAllProviders() {
        return providerRepository.findAll();
    }

    // Fetch a unique Provider
    public Provider getProviderById(int providerId) {
        return providerRepository.findById(providerId).orElse(null);
    }

    // Fetch a Provider by providername
    public Provider getProviderByProvidername(String providername) {
        return providerRepository.getProviderByProvidername(providername);
    }

    // Add a new Provider
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

    // Delete a unique Provider
    public void deleteProviderById(int providerId) {
        providerRepository.deleteById(providerId);
    }

    //Find by status using enum
    public List<Provider> getProvidersByStatus(Provider.ProviderStatus status) {
        return providerRepository.findByStatus(status);
    }

    public List<Provider> getProvidersByService(int serviceId) {
        return providerRepository.findByServices_ServiceId(serviceId);
    }

}
