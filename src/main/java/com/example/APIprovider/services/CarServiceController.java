package com.example.APIprovider.services;

import com.example.APIprovider.provider.Provider;
import com.example.APIprovider.provider.ProviderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@Controller
@RequestMapping("/service")
public class CarServiceController {

    @Autowired
    public CarServiceService serviceService;
    @Autowired
    private ProviderService providerService;

    @GetMapping("/services")
    public String viewProviderServices(HttpSession session, Model model) {
        Object providerIdObj = session.getAttribute("providerId");
        if (providerIdObj == null) {
            return "redirect:/providers/login";
        }

        int providerId = (int) providerIdObj;

        Provider provider = providerService.getProviderById(providerId);
        List<CarService> providerServices = serviceService.getServicesByProviderId(providerId);
        List<CarService> unassignedServices = serviceService.getUnassignedServices();

        model.addAttribute("provider", provider);
        model.addAttribute("providerId", providerId);
        model.addAttribute("providerServices", providerServices);
        model.addAttribute("unassignedServices", unassignedServices);
        model.addAttribute("title", "Manage Services");

        return "provider/service-list";
    }

    @GetMapping("/services/add")
    public String showAddServicePage(HttpSession session, Model model) {
        Provider provider = (Provider) session.getAttribute("loggedInProvider");
        if (provider == null) return "redirect:/providers/login";

        List<CarService> unassignedServices = serviceService.getUnassignedServices();

        model.addAttribute("provider", provider);
        model.addAttribute("unassignedServices", unassignedServices);
        model.addAttribute("title", "Add Service");

        return "provider/service-add";
    }

    @PostMapping("/assign")
    public ResponseEntity<String> assignServiceToProvider(
            @RequestParam int serviceId,
            @RequestParam int providerId) {
        serviceService.assignServiceToProvider(serviceId, providerId);
        return new ResponseEntity<>("Service successfully assigned to provider", HttpStatus.OK);
    }

    @PostMapping("/unassign")
    public String unassignServiceFromProvider(@RequestParam int serviceId, HttpSession session) {
        Provider provider = (Provider) session.getAttribute("loggedInProvider");
        if (provider == null) return "redirect:/providers/login";

        CarService service = serviceService.getServiceById(serviceId);
        if (service != null) {
            service.setProvider(null);
            serviceService.saveService(service);
        }

        return "redirect:/service/services";
    }


    /**
     * http://localhost:8081/service/all
     */
    @GetMapping("/all")
    public Object getAllServices() {
        return new ResponseEntity<>(serviceService.getAllServices(), HttpStatus.OK);
    }



}
