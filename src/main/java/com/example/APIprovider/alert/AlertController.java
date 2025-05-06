package com.example.APIprovider.alert;

import com.example.APIprovider.provider.Provider;
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


    //  Delete an alert by ID (triggered from UI form)
    @PostMapping("/delete/{alertId}")
    public String deleteAlert(@PathVariable int alertId) {
        alertService.deleteAlertById(alertId);
        return "redirect:/alerts?providerId=4"; // Replace with dynamic if needed
    }

}

//
//import jakarta.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import com.example.APIprovider.provider.Provider;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/alerts")
//public class AlertController {
//
//    @Autowired
//    private AlertService alertService;
//
//    @GetMapping
//    public String viewAllAlerts(@RequestParam(required = false) String type,
//                                HttpSession session,
//                                Model model) {
//        Provider provider = (Provider) session.getAttribute("loggedInProvider");
//        if (provider == null) {
//            return "redirect:/providers/login";
//        }
//
//        List<Alert> alertList = (type != null && !type.isEmpty())
//                ? alertService.getAlertsByType(type)
//                : alertService.getAlertsByProviderId(provider.getProviderId());
//
//        model.addAttribute("alertList", alertList);
//        model.addAttribute("filter", (type != null && !type.isEmpty()) ? type : "All");
//        model.addAttribute("providerId", provider.getProviderId());
//        model.addAttribute("title", "All Alerts");
//
//        return "provider/alert-list";
//    }
//
//
//    //  Delete an alert by ID (triggered from UI form)
//    @PostMapping("/delete/{alertId}")
//    public String deleteAlert(@PathVariable int alertId, HttpSession session) {
//        Provider provider = (Provider) session.getAttribute("loggedInProvider");
//        if (provider == null) {
//            return "redirect:/providers/login";
//        }
//
//        alertService.deleteAlertById(alertId);
//        return "redirect:/alerts";
//    }
//
//    //  API endpoint: Create a new alert (not used in view)
//    @PostMapping("/create")
//    public ResponseEntity<Alert> createAlert(@RequestBody Alert alert) {
//        Alert newAlert = alertService.createAlert(
//                alert.getProviderId(),
//                alert.getUserId(),
//                alert.getAppointmentId(),
//                alert.getAlertType(),
//                alert.getMessage()
//        );
//        return ResponseEntity.ok(newAlert);
//    }
//
//    //  API endpoint: Get all alerts for a specific provider
//    @GetMapping("/provider/{providerId}")
//    public List<Alert> getAlertsByProviderId(@PathVariable int providerId) {
//        return alertService.getAlertsByProviderId(providerId);
//    }
//
//    //  API endpoint: Get alerts by provider and type
//    @GetMapping("/provider/{providerId}/type/{alertType}")
//    public List<Alert> getAlertsByProviderIdAndAlertType(@PathVariable int providerId,
//                                                         @PathVariable String alertType) {
//        return alertService.getAlertsByProviderIdAndAlertType(providerId, alertType);
//    }
//
//    //  API endpoint: Get alerts by appointment
//    @GetMapping("/appointment/{appointmentId}")
//    public List<Alert> getAlertsByAppointmentId(@PathVariable int appointmentId) {
//        return alertService.getAlertsByAppointmentId(appointmentId);
//    }
//}
