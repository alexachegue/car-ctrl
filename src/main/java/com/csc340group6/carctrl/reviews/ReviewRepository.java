package com.csc340group6.carctrl.reviews;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findByUser_UserId(int userId);
    List<Review> findByProvider_ProviderId(int providerId);
    Optional<Review> findByAppointment_AppointmentId(int appointmentId);

}
