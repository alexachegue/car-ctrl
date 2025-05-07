package com.example.APIprovider.stats;

import com.example.APIprovider.provider.Provider;
import com.example.APIprovider.provider.ProviderRepository;
import com.example.APIprovider.services.CarService;
import com.example.APIprovider.services.CarServiceRepository;
import com.example.APIprovider.reviews.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatsService {

    @Autowired
    private CarServiceRepository serviceRepo;

    @Autowired
    private ReviewRepository reviewRepo;

    @Autowired
    private ProviderRepository providerRepository;

    public List<ServiceStats> getProviderStats(int providerId) {
        Provider provider = providerRepository.findById(providerId).orElse(null);
        if (provider == null) return new ArrayList<>();

        List<CarService> services = serviceRepo.findByProvider(provider);
        List<ServiceStats> statsList = new ArrayList<>();

        for (CarService service : services) {
            long totalRequests = reviewRepo.countByProviderAndService(providerId, service.getServiceId());
            Double avgRating = reviewRepo.findAverageRatingByProviderAndService(providerId, service.getServiceId());
            if (avgRating == null) avgRating = 0.0;
            List<String> feedback = reviewRepo.findCommentsByProviderAndService(providerId, service.getServiceId());
            if (feedback == null) feedback = new ArrayList<>();
            statsList.add(new ServiceStats(service.getName(), totalRequests, avgRating, feedback));
        }

        return statsList;
    }
}
