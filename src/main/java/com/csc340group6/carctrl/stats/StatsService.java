package com.csc340group6.carctrl.stats;

import com.csc340group6.carctrl.provider.Provider;
import com.csc340group6.carctrl.provider.ProviderRepository;
import com.csc340group6.carctrl.services.CarService;
import com.csc340group6.carctrl.services.CarServiceRepository;
import com.csc340group6.carctrl.reviews.ReviewRepository;
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
