package com.csc340group6.carctrl.subscription;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findAppointmentByUser_UserId(int userId);

    @Query("SELECT a FROM Appointment a WHERE a.service.provider.providerId = :providerId")
    List<Appointment> getAppointmentsByProvider(@Param("providerId") int providerId);
}