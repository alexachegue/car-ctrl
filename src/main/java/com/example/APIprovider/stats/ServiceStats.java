package com.example.APIprovider.stats;

import java.util.List;

public class ServiceStats {
    private String serviceName;
    private long totalRequests;
    private double averageRating;
    private List<String> customerFeedback;

    public ServiceStats() {
    }

    public ServiceStats(String serviceName, long totalRequests, double averageRating, List<String> customerFeedback) {
        this.serviceName = serviceName;
        this.totalRequests = totalRequests;
        this.averageRating = averageRating;
        this.customerFeedback = customerFeedback;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public long getTotalRequests() {
        return totalRequests;
    }

    public void setTotalRequests(long totalRequests) {
        this.totalRequests = totalRequests;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public List<String> getCustomerFeedback() {
        return customerFeedback;
    }

    public void setCustomerFeedback(List<String> customerFeedback) {
        this.customerFeedback = customerFeedback;
    }
}

