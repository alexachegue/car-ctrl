package com.csc340group6.carctrl.alert;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/alerts")
public class AlertController {

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private AlertService alertService;

    /**
     *  CUSTOMER MVC
     */
    @GetMapping
    public String showAlerts(HttpSession session, Model model) {
        Object userIdObj = session.getAttribute("userId");
        if (userIdObj == null) return "redirect:/users/login-page";

        int userId = (int) userIdObj;

        List<Alert> alerts = alertRepository.findByUser_UserIdOrderByCreatedAtDesc(userId);
        model.addAttribute("alerts", alerts);
        return "user/alerts";
    }

    /**
     *  PROVIDER MVC
     */
    //  Main FreeMarker view of alerts with optional type filter and providerId from request param
    @GetMapping
    public String viewAllAlerts(@RequestParam(required = false) String type,
                                @RequestParam int providerId,
                                Model model) {
        List<Alert> alertList;

        if (type != null && !type.isEmpty()) {
            alertList = alertService.getAlertsByType(type);
            model.addAttribute("filter", type);
        } else {
            alertList = alertService.getAllAlerts();
            model.addAttribute("filter", "All");
        }

        model.addAttribute("alertList", alertList);
        model.addAttribute("providerId", providerId);
        model.addAttribute("title", "All Alerts");

        return "provider/alert-list"; // this maps to your alert-list.ftlh
    }

    //  Delete an alert by ID (triggered from UI form)
    @PostMapping("/delete/{alertId}")
    public String deleteAlert(@PathVariable int alertId) {
        alertService.deleteAlertById(alertId);
        return "redirect:/alerts?providerId=4"; // Replace with dynamic if needed
    }

}