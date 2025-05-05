package com.example.APIprovider.provider;

import com.example.APIprovider.services.CarService;
import com.example.APIprovider.services.CarServiceService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/providers")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @Autowired
    private CarServiceService carServiceService;

    @GetMapping("/profile/{providerId}")
    public String viewProfile(@PathVariable int providerId, Model model) {
        Provider provider = providerService.getProviderById(providerId);
        List<CarService> providerServices = carServiceService.getServicesByProviderId(providerId);

        model.addAttribute("provider", provider);
        model.addAttribute("providerServices", providerServices);
        model.addAttribute("title", "Profile: " + provider.getProvidername());
        return "provider/profile";
    }

    @GetMapping("/edit/{providerId}")
    public String editForm(@PathVariable int providerId, Model model) {
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        model.addAttribute("title", "Edit Profile");
        return "provider/profile-edit";
    }

    @PostMapping("/update/{providerId}")
    public String updateProvider(@PathVariable int providerId, Provider provider) {
        providerService.updateProvider(providerId, provider);
        return "redirect:/providers/profile/" + providerId;
    }

    @GetMapping("/register-form")
    public String showRegisterForm(Model model) {
        model.addAttribute("provider", new Provider());
        return "provider-register";
    }

    @PostMapping("/register-form")
    public String handleRegister(@ModelAttribute Provider provider, HttpSession session) {
        if (provider.getActiveDate() == null) {
            provider.setActiveDate(new Date());
        }

        providerService.addNewProvider(provider);
        session.setAttribute("loggedInProvider", provider);
        return "redirect:/providers/login-page";
    }

    @GetMapping("/login-page")
    public String showLoginPage(Model model) {
        model.addAttribute("provider", new Provider());
        return "login";
    }

    @PostMapping("/login-page")
    public String handleLogin(@ModelAttribute Provider provider, HttpSession session, Model model) {
        Provider foundProvider = providerService.getByProvidername(provider.getProvidername());
        session.setAttribute("loggedInProvider", foundProvider);


        if (foundProvider == null || !foundProvider.getPassword().equals(provider.getPassword())) {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
        return "redirect:/providers/home";
    }

    @GetMapping("/home")
        public String showProviderHome(@RequestParam(required = false) Integer providerId, Model model) {
    if (providerId == null) {
        return "redirect:/providers/login-page";
    }

    Provider provider = providerService.getProviderById(providerId);
    model.addAttribute("providerId", providerId);
    model.addAttribute("provider", provider);


    // Completion percentage calculation
    int filledFields = 0;
    int totalFields = 6;

    if (provider.getProvidername() != null && !provider.getProvidername().isEmpty()) filledFields++;
    if (provider.getEmail() != null && !provider.getEmail().isEmpty()) filledFields++;
    if (provider.getPhone() != null && !provider.getPhone().isEmpty()) filledFields++;
    if (provider.getAddress() != null && !provider.getAddress().isEmpty()) filledFields++;
    if (provider.getOperatingHours() != null && !provider.getOperatingHours().isEmpty()) filledFields++;
    if (provider.getBio() != null && !provider.getBio().isEmpty()) filledFields++;

    int profileCompletion = (int) ((filledFields / (double) totalFields) * 100);
    model.addAttribute("profileCompletion", profileCompletion);

    return "provider/home";
}

    // http://localhost:8081/providers/all
    @GetMapping("/all")
    public ResponseEntity<List<Provider>> getAllProviders() {
        return new ResponseEntity<>(providerService.getAllProviders(), HttpStatus.OK);
    }

    // http://localhost:8081/providers/4
    @GetMapping("/{providerId}")
    public ResponseEntity<Provider> getProviderById(@PathVariable int providerId) {
        return new ResponseEntity<>(providerService.getProviderById(providerId), HttpStatus.OK);
    }

    // http://localhost:8081/providers/delete/7
    @DeleteMapping("/delete/{providerId}")
    public ResponseEntity<List<Provider>> deleteProviderById(@PathVariable int providerId) {
        providerService.deleteProviderById(providerId);
        return new ResponseEntity<>(providerService.getAllProviders(), HttpStatus.OK);
    }

}
