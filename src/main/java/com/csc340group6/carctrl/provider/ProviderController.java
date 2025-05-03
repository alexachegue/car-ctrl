package com.csc340group6.carctrl.provider;
import com.csc340group6.carctrl.services.CarService;
import com.csc340group6.carctrl.services.CarServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.csc340group6.carctrl.services.CarServiceRepository;

import java.util.List;

@Controller
@RequestMapping("/providers")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @Autowired
    private CarServiceRepository carServiceRepository;

    @Autowired
    private CarServiceService carServiceService;

    /**

    // Get a list of all Providers
    @GetMapping("/all")
    public ResponseEntity<List<Provider>> getAllProviders() {
        return new ResponseEntity<>(service.getAllProviders(), HttpStatus.OK);
    }

    // Get a specific Provider by Id
    @GetMapping("/{providerId}")
    public ResponseEntity<Provider> getProviderById(@PathVariable int providerId) {
        return new ResponseEntity<>(service.getProviderById(providerId), HttpStatus.OK);
    }

    // Register a new Provider
    @PostMapping("/register")
    public ResponseEntity<String> addNewProvider(@RequestBody Provider provider) {
        service.addNewProvider(provider);
        return new ResponseEntity<>("New Provider Successfully Created!", HttpStatus.CREATED);
    }

    // Update an existing Provider
    @PutMapping("/update/{providerId}")
    public ResponseEntity<String> updateProvider(@PathVariable int providerId, @RequestBody Provider provider) {
        service.updateProvider(providerId, provider);
        return new ResponseEntity<>("Provider updated successfully", HttpStatus.OK);
    }

    // Delete a Provider
    @DeleteMapping("/delete/{providerId}")
    public ResponseEntity<List<Provider>> deleteProviderById(@PathVariable int providerId) {
        service.deleteProviderById(providerId);
        return new ResponseEntity<>(service.getAllProviders(), HttpStatus.OK);
    }

     **/


    @GetMapping("/{providerId}/services")
    public ResponseEntity<List<CarService>> getServicesByProvider(@PathVariable int providerId) {
        Provider provider = providerService.getProviderById(providerId);
        return new ResponseEntity<>(provider.getServices(), HttpStatus.OK);
    }

    @GetMapping("/by-service/{serviceId}")
    public String getProvidersByService(@PathVariable int serviceId, Model model) {
        List<Provider> providers = providerService.getProvidersByService(serviceId);
        CarService service = carServiceService.getCarServiceById(serviceId);

        model.addAttribute("service", service);
        model.addAttribute("providers", providers);

        return "providers-by-service";
    }
}
