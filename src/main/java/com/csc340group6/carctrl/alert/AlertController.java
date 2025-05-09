package com.csc340group6.carctrl.alert;

import com.csc340group6.carctrl.provider.Provider;
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
    @GetMapping("/user")
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
    @GetMapping
    public String viewAllAlerts(@RequestParam(required = false) String type,
                                HttpSession session,
                                Model model) {
        Object providerObj = session.getAttribute("loggedInProvider");
        if (providerObj == null) {
            return "redirect:/providers/login";
        }

        Provider provider = (Provider) providerObj;
        int providerId = provider.getProviderId();

        List<Alert> alertList;
        if (type != null && !type.isEmpty()) {
            alertList = alertService.getAlertsByProviderIdAndAlertType(providerId, Alert.AlertType.valueOf(type));
            model.addAttribute("filter", type);
        } else {
            alertList = alertService.getAlertsByProviderId(providerId);
            model.addAttribute("filter", "All");
        }

        model.addAttribute("alertList", alertList);
        model.addAttribute("providerId", providerId);
        model.addAttribute("title", "All Alerts");

        return "provider/alert-list";
    }


    @PostMapping("/delete/{alertId}")
    public String deleteAlert(@PathVariable int alertId) {
        alertService.deleteAlertById(alertId);
        return "redirect:/alerts?providerId=4";
    }

}