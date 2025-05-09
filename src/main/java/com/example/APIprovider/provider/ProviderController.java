package com.example.APIprovider.provider;

import com.example.APIprovider.services.CarService;
import com.example.APIprovider.services.CarServiceService;
import com.example.APIprovider.stats.ServiceStats;
import com.example.APIprovider.stats.StatsService;
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

    @Autowired
    private StatsService statsService;


    @GetMapping("/")
    public String showLandingPage() {
        return "provider/first-page";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("provider", new Provider());
        return "provider/provider-register";
    }

    @PostMapping("/register")
    public String handleRegister(@ModelAttribute Provider provider, HttpSession session) {
        if (provider.getActiveDate() == null) {
            provider.setActiveDate(new Date());
        }

        providerService.addNewProvider(provider);
        session.setAttribute("loggedInProvider",provider);
        session.setAttribute("providerId",provider.getProviderId());

        return "redirect:/providers/login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("provider", new Provider());
        return "provider/provider-login";
    }

    @PostMapping("/login")
    public String handleLogin(@ModelAttribute Provider provider, HttpSession session, Model model) {
        Provider foundProvider = providerService.getByProvidername(provider.getProvidername());

        if (foundProvider == null || !foundProvider.getPassword().equals(provider.getPassword())) {
            model.addAttribute("error", "Invalid credentials");
            return "provider/provider-login";
        }

        session.setAttribute("loggedInProvider", foundProvider);
        session.setAttribute("providerId", foundProvider.getProviderId());
        return "redirect:/providers/home?providerId=" + foundProvider.getProviderId();
    }

    @GetMapping("/home")
    public String showProviderHome(HttpSession session, Model model) {
        Provider provider = (Provider) session.getAttribute("loggedInProvider");
        if (provider == null) {
            return "redirect:/providers/login";
        }

        //Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("providerId", provider.getProviderId());
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

    @GetMapping("/profile")
    public String viewProfile(HttpSession session, Model model) {
        Provider sessionProvider = (Provider) session.getAttribute("loggedInProvider");
        if (sessionProvider == null) {
            return "redirect:/providers/login";
        }

        Provider provider = providerService.getProviderById(sessionProvider.getProviderId());

        session.setAttribute("loggedInProvider", provider);

        model.addAttribute("provider", provider);
        model.addAttribute("providerServices", carServiceService.getServicesByProviderId(provider.getProviderId()));
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

    @GetMapping("/stats")
    public String viewStats(HttpSession session, Model model) {
        Provider provider = (Provider) session.getAttribute("loggedInProvider");
        if (provider == null) {
            return "redirect:/providers/login";
        }

        List<ServiceStats> stats = statsService.getProviderStats(provider.getProviderId());

        model.addAttribute("stats", stats);
        return "provider/statistics";
    }



    @PostMapping("/update")
    public String updateProvider(@RequestParam int providerId, @ModelAttribute Provider provider) {
        providerService.updateProvider(providerId, provider);
        return "redirect:/providers/profile";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/providers/login";
    }

}
