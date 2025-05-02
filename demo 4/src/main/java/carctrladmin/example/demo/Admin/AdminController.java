package com.csc340group6.carctrl.Admin;

import com.csc340group6.carctrl.provider.Provider;
import com.csc340group6.carctrl.user.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")

public class AdminController {

    @Autowired
    private AdminService adminService;

    // Creating Admin
    @GetMapping("/all")
    public Object getAllAdmins(Model model) {
        model.addAttribute("adminList", adminService.getAllAdmins());
        model.addAttribute("title", "All Admins");

        return "admin-list";
    }

    @GetMapping("/adminlogin-page")
    public String adminLogIn(Model model) {
        model.addAttribute("adminUser", new com.csc340group6.carctrl.Admin.Admin());
        return "admin-login";
    }
    @PostMapping("/adminlogin-page")
    public String handleLogIn(@ModelAttribute com.csc340group6.carctrl.Admin.Admin admin, HttpSession session) {
        if (admin.getUsername() == null) {
            return "Not in database";
        }

        adminService.addNewAdmin(admin);
        session.setAttribute("AdminLoggedIn", admin);
        return "redirect:/cars/register-car";
    }




}
