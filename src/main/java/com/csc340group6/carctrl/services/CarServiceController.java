package com.csc340group6.carctrl.services;

import com.csc340group6.carctrl.provider.Provider;
import com.csc340group6.carctrl.provider.ProviderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.csc340group6.carctrl.services.CarService;

import java.util.List;

@Controller
@RequestMapping("/services")
public class CarServiceController {

    @Autowired
    public CarServiceService carServiceService;

    /**
     *  CUSTOMER MVC
     */
    @GetMapping("/explore")
    public String showExplorePage(Model model) {
        System.out.println("Explore page handler triggered");
        model.addAttribute("categories", CarService.ServiceCategory.values());
        return "user/explore";
    }

    @GetMapping("/service-category/{category}")
    public String getServicesByCategory(@PathVariable CarService.ServiceCategory category, Model model) {
        List<CarService> services = carServiceService.getCarServicesByCategory(category);
        model.addAttribute("services", services);
        model.addAttribute("selectedCategory", category.name());
        return "user/services";
    }


    /**
     * PROVIDER MVC
     */
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

        return "redirect:/services/services";
    }
}