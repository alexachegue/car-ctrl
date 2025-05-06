package com.csc340group6.carctrl.services;

import org.springframework.beans.factory.annotation.Autowired;
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
     * http://localhost:8081/service/all
     *
    @GetMapping("/all")
    public Object getAllServices(){
        return new ResponseEntity<>(serviceService.getAllServices(), HttpStatus.OK);
    }
    */
}