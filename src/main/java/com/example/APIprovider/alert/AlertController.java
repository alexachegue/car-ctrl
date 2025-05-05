package com.example.APIprovider.alert;

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
    private AlertService alertService;

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

    //  API endpoint: Create a new alert (not used in view)
    @PostMapping("/create")
    public ResponseEntity<Alert> createAlert(@RequestBody Alert alert) {
        Alert newAlert = alertService.createAlert(
                alert.getProviderId(),
                alert.getUserId(),
                alert.getAppointmentId(),
                alert.getAlertType(),
                alert.getMessage()
        );
        return ResponseEntity.ok(newAlert);
    }

    //  API endpoint: Get all alerts for a specific provider
    @GetMapping("/provider/{providerId}")
    public List<Alert> getAlertsByProviderId(@PathVariable int providerId) {
        return alertService.getAlertsByProviderId(providerId);
    }

    //  API endpoint: Get alerts by provider and type
    @GetMapping("/provider/{providerId}/type/{alertType}")
    public List<Alert> getAlertsByProviderIdAndAlertType(@PathVariable int providerId,
                                                         @PathVariable String alertType) {
        return alertService.getAlertsByProviderIdAndAlertType(providerId, alertType);
    }

    //  API endpoint: Get alerts by appointment
    @GetMapping("/appointment/{appointmentId}")
    public List<Alert> getAlertsByAppointmentId(@PathVariable int appointmentId) {
        return alertService.getAlertsByAppointmentId(appointmentId);
    }
}
