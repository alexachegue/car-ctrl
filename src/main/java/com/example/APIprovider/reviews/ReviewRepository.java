package com.example.APIprovider.reviews;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findByUser_UserId(int userId);
    List<Review> findByProvider_ProviderId(int providerId);
    Optional<Review> findByAppointment_AppointmentId(int appointmentId);

    // ✅ Corrected: Count reviews by navigating relationships
    @Query("SELECT COUNT(r) FROM Review r WHERE r.provider.providerId = :providerId AND r.appointment.service.serviceId = :serviceId")
    long countByProviderAndService(@Param("providerId") int providerId, @Param("serviceId") int serviceId);

    // ✅ Already correct (you had this one right)
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.provider.providerId = :providerId AND r.appointment.service.serviceId = :serviceId")
    Double findAverageRatingByProviderAndService(@Param("providerId") int providerId, @Param("serviceId") int serviceId);

    // ✅ Corrected: Same fix for accessing nested attributes
    @Query("SELECT r.description FROM Review r WHERE r.provider.providerId = :providerId AND r.appointment.service.serviceId = :serviceId AND r.description IS NOT NULL")
    List<String> findCommentsByProviderAndService(@Param("providerId") int providerId, @Param("serviceId") int serviceId);
}
