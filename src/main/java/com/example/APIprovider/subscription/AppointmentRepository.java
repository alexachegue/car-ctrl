package com.example.APIprovider.subscription;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
        List<Appointment> findAppointmentByUser_UserId(int userId);

//    List<Appointment> findByUser_UserId(int userId);
//
//    // Corrected method names based on entity relationships
//    List<Appointment> findByService_Provider_ProviderId(int providerId);
//
//    List<Appointment> findByService_Provider_ProviderIdAndStatus(int providerId, String status);
//
//    // Custom JPQL queries for better debugging
//    @Query("SELECT a FROM Appointment a WHERE a.service.provider.providerId = :providerId")
//    List<Appointment> getAppointmentsByProvider(@Param("providerId") int providerId);
//
//    @Query("SELECT a FROM Appointment a WHERE a.service.provider.providerId = :providerId AND a.status = :status")
//    List<Appointment> getAppointmentsByProviderAndStatus(@Param("providerId") int providerId, @Param("status") String status);
        @Query("SELECT a FROM Appointment a WHERE a.service.provider.providerId = :providerId")
        List<Appointment> getAppointmentsByProvider(@Param("providerId") int providerId);

}
