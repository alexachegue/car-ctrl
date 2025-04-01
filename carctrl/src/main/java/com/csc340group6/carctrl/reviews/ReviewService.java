package com.csc340group6.carctrl.reviews;
import com.csc340group6.carctrl.subscription.Appointment;
import com.csc340group6.carctrl.subscription.AppointmentRepository;
import com.csc340group6.carctrl.user.User;
import com.csc340group6.carctrl.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review getReviewById(int id) {
        return reviewRepository.findById(id).orElse(null);
    }

    public List<Review> getReviewsByUserId(int userId) {
        return reviewRepository.findByUser_UserId(userId);
    }

    public List<Review> getReviewsByProviderId(int providerId) {
        return reviewRepository.findByProviderId(providerId);
    }

    public Review createReview(Review review) {
        User user = userRepository.findById(review.getUser().getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Appointment appointment = appointmentRepository.findById(review.getAppointment().getAppointmentId())
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        review.setUser(user);
        review.setAppointment(appointment);

        return reviewRepository.save(review);
    }

    public Review respondToReview(int reviewId, String response) {
        Review review = getReviewById(reviewId);
        if (review != null) {
            review.setProviderResponse(response);
            return reviewRepository.save(review);
        }
        return null;
    }

    public void deleteReview(int id) {
        reviewRepository.deleteById(id);
    }
}