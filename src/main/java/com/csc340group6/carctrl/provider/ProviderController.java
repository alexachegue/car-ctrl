package com.csc340group6.carctrl.provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/providers")
public class ProviderController {

    @Autowired
    private ProviderService service;

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
}
