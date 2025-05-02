package com.csc340group6.carctrl.Admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<com.csc340group6.carctrl.Admin.Admin, Integer> {

    com.csc340group6.carctrl.Admin.Admin getAdminByUsername(String adminusername);

    List<com.csc340group6.carctrl.Admin.Admin> findByAdminID (int adminID);

}
