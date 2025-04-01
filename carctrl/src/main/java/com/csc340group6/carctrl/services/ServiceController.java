package com.csc340group6.carctrl.services;


import com.csc340group6.carctrl.user.User;
import com.csc340group6.carctrl.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    public ServiceService serviceService;

    /**
     * http://localhost:8081/service/all
     */
    @GetMapping("/all")
    public Object getAllServices(){
        return new ResponseEntity<>(serviceService.getAllServices(), HttpStatus.OK);
    }
}
