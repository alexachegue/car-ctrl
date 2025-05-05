package com.example.APIprovider.services;

import com.example.APIprovider.provider.Provider;
import com.example.APIprovider.provider.ProviderService;
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


    /**
     * http://localhost:8081/service/all
     */
    @GetMapping("/all")
    public Object getAllServices() {
        return new ResponseEntity<>(serviceService.getAllServices(), HttpStatus.OK);
    }



    // GET all services not yet assigned to any provider
//    @GetMapping("/unassigned")
//    public ResponseEntity<List<Service>> getUnassignedServices() {
//        return new ResponseEntity<>(serviceService.getUnassignedServices(), HttpStatus.OK);
//    }
//
//    // POST: Assign a service to a provider
//    @PostMapping("/assign")
//    public ResponseEntity<String> assignServiceToProvider(
//            @RequestParam int serviceId,
//            @RequestParam int providerId) {
//        serviceService.assignServiceToProvider(serviceId, providerId);
//        return new ResponseEntity<>("Service successfully assigned to provider", HttpStatus.OK);
//    }

    @GetMapping("/services/{providerId}")
    public String viewProviderServices(@PathVariable int providerId, Model model) {
        Provider provider = providerService.getProviderById(providerId);
        if (provider == null) return "redirect:/error";

        List<CarService> providerServices = serviceService.getServicesByProviderId(providerId);
        List<CarService> unassignedServices = serviceService.getUnassignedServices();

        model.addAttribute("provider", provider);
        model.addAttribute("providerServices", providerServices);
        model.addAttribute("unassignedServices", unassignedServices);
        model.addAttribute("title", "Manage Services");

        return "provider/service-list";
    }

    @GetMapping("/services/add/{providerId}")
    public String showAddServicePage(@PathVariable int providerId, Model model) {
        Provider provider = providerService.getProviderById(providerId);  // Add this line
        List<CarService> unassignedServices = serviceService.getUnassignedServices();

        model.addAttribute("provider", provider); // instead of just providerId
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
    public String unassignServiceFromProvider(@RequestParam int serviceId) {
        CarService service = serviceService.getServiceById(serviceId);
        if (service != null) {
            service.setProvider(null);
            serviceService.saveService(service);
        }
        return "redirect:/service/services/" + (service != null && service.getProvider() != null ? service.getProvider().getProviderId() : "");
    }


}
