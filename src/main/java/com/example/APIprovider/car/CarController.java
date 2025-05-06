package com.example.APIprovider.car;

import com.example.APIprovider.user.User;
import com.example.APIprovider.user.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Controller
@RequestMapping("/cars")
public class CarController {

    @Autowired
    public CarService carService;

    @Autowired
    public UserService userService;

    /**
     * Show the car registration form
     */
    @GetMapping("/register-car")
    public String showCarForm(Model model) {
        model.addAttribute("car", new Car());
        return "user/car-register";
    }

    /**
     * Handle form submission and save car
     */
    @PostMapping("/register-car")
    public String registerCar(@ModelAttribute Car car, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/users/login-page";
        }

        car.setUser(user);
        carService.addNewCar(car);

        return "redirect:/users/profile-page";
    }
}