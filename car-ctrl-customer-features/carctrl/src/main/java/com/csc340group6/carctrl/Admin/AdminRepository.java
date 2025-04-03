package com.csc340group6.carctrl.Admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import java.util.*;


@Repository
public interface AdminRepository {

    // Find all admins (already provided by JpaRepository)
    List<Admin> findAll();

    // Find admin by ID (already provided by JpaRepository)
    Optional<Admin> findById(Long id);

    // Save an admin (already provided by JpaRepository)
    Admin save(Admin admin);

    // Delete an admin by ID (already provided by JpaRepository)
    void deleteById(Long id);
}
