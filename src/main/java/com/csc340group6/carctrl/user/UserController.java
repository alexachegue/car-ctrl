package com.csc340group6.carctrl.user;

import com.csc340group6.carctrl.car.Car;
import com.csc340group6.carctrl.car.CarService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private CarService carService;


    /**
    /**
     * Return a list of users (API use).
     *
    @GetMapping("/all")
    @ResponseBody
    public Object getAllUser(){
        return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
    }

    /**
     * Get user by ID (API use).
     *
    @GetMapping("/{userId}")
    @ResponseBody
    public Object getUser(@PathVariable int userId){
        return new ResponseEntity<>(service.getUserById(userId), HttpStatus.OK);
    }

    /**
     * Register new user via JSON (API use).
     *
    @PostMapping("/register")
    @ResponseBody
    public Object addNewUser(@RequestBody User user){
        if (user.getCars() != null) {
            user.getCars().forEach(car -> car.setUser(user));
        }
        service.addNewUser(user);
        return new ResponseEntity<>("New User Successfully Created!", HttpStatus.CREATED);
    }

    /**
     * Update user details (API use).
     *
    @PostMapping("/update/{userId}")
    @ResponseBody
    public Object updateUser(@PathVariable int userId, @RequestBody User user) {
        User existingUser = service.getUserById(userId);
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        if (user.getUsername() != null) existingUser.setUsername(user.getUsername());
        if (user.getPassword() != null) existingUser.setPassword(user.getPassword());
        if (user.getEmail() != null) existingUser.setEmail(user.getEmail());
        if (user.getPhoneNumber() != null) existingUser.setPhoneNumber(user.getPhoneNumber());
        if (user.getDateJoined() != null) existingUser.setDateJoined(user.getDateJoined());

        service.updateUser(existingUser);
        return ResponseEntity.ok("User updated successfully");
    }

    **/

    @GetMapping("/")
    public String showLandingPage() {
        return "user/first-page";
    }


    /**
     * Show register form (MVC)
     */
    @GetMapping("/register-form")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "user/register";
    }

    @PostMapping("/register-form")
    public String handleRegister(
            @ModelAttribute User user, @RequestParam("fileUpload") MultipartFile file, HttpSession session) throws IOException {

        if (user.getDateJoined() == null) {
            user.setDateJoined(new Date());
        }

        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();

            String baseDir = System.getProperty("user.dir");
            String uploadDir = baseDir + "/uploads/profile_pictures/";

            File uploadPath = new File(uploadDir);
            if (!uploadPath.exists()) uploadPath.mkdirs();

            File destination = new File(uploadPath, fileName);
            file.transferTo(destination);

            user.setProfilePicture(fileName);
        }


        service.addNewUser(user);
        session.setAttribute("loggedInUser", user);
        session.setAttribute("userId", user.getUserId());

        return "redirect:/cars/register-car";
    }


    /**
     * Show login form (MVC)
     */
    @GetMapping("/login-page")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "user/login";
    }

    /**
     * Handle login submission (MVC)
     */
    @PostMapping("/login-page")
    public String handleLogin(@ModelAttribute User user, HttpSession session, Model model) {
        User foundUser = service.getByUsername(user.getUsername());

        if (foundUser == null || !foundUser.getPassword().equals(user.getPassword())) {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }

        session.setAttribute("loggedInUser", foundUser);
        session.setAttribute("userId", foundUser.getUserId());

        return "redirect:/users/home-page";

    }

    /**
     * Show profile page if logged in (MVC)
     */
    @GetMapping("/profile-page")
    public String profile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/users/login-page";
        }

        List<Car> cars = carService.findCarsByUser(user.getUserId());
        model.addAttribute("cars", cars);
        model.addAttribute("user", user);

        return "user/profile";
    }

    @GetMapping("/edit-profile")
    public String showEditForm(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/users/login-page";
        }

        model.addAttribute("user", user);
        return "user/edit-profile";
    }

    @PostMapping("/edit-profile")
    public String handleEditProfile(@ModelAttribute User updatedUser,
                                    @RequestParam("fileUpload") MultipartFile file,
                                    HttpSession session) throws IOException {
        User currentUser = (User) session.getAttribute("loggedInUser");
        if (currentUser == null) {
            return "redirect:/users/login-page";
        }

        currentUser.setUsername(updatedUser.getUsername());
        currentUser.setPassword(updatedUser.getPassword());
        currentUser.setEmail(updatedUser.getEmail());
        currentUser.setPhoneNumber(updatedUser.getPhoneNumber());

        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String uploadDir = System.getProperty("user.dir") + "/uploads/profile_pictures/";
            File uploadPath = new File(uploadDir);
            if (!uploadPath.exists()) uploadPath.mkdirs();

            File destination = new File(uploadPath, fileName);
            file.transferTo(destination);

            currentUser.setProfilePicture(fileName);
        }

        service.updateUser(currentUser);
        session.setAttribute("loggedInUser", currentUser);

        return "redirect:/users/profile-page";
    }

    /**
     * Log out the user (MVC)
     */
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/users/login-page";
    }

    @GetMapping("/home-page")
    public String homePage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("dateJoined", user.getDateJoined());
        }
        return "user/home-index";
    }

    @PostMapping("/home-page")
    public String displayHomePage(){
        return "user/home-index";
    }

}

