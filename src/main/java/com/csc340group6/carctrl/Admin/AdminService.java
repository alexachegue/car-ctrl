package com.csc340group6.carctrl.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminService {


    @Autowired
    private com.csc340group6.carctrl.Admin.AdminRepository adminRepository;

    public List<com.csc340group6.carctrl.Admin.Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public List<com.csc340group6.carctrl.Admin.Admin> getAdminById(int adminID) {
        return adminRepository.findByAdminID(adminID);

    }

    public com.csc340group6.carctrl.Admin.Admin getAdminByUsername(String adminusername) {
        return adminRepository.getAdminByUsername(adminusername);
    }

    public void addNewAdmin(com.csc340group6.carctrl.Admin.Admin admin) {
        adminRepository.save(admin);
    }

    // Removing a admin

    public void deleteAdminByID(int adminID) {

        adminRepository.deleteById(adminID);
    }
}
