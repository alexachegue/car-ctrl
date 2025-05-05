package com.example.APIprovider.car;

//import com.csc340group6.carctrl.user.UserService;

import com.example.APIprovider.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    public CarService carService;

    @Autowired
    public UserService userService;

    /**
     * http://localhost:8081/cars/all
     */
    @GetMapping("/all")
    public Object getAllCars(){
        return new ResponseEntity<>(carService.getAllCars(), HttpStatus.OK);
    }

    /**
     * http://localhost:8081/cars/{carId}
     */
    @GetMapping("/{carId}")
    public Object getCarById(@PathVariable int carId){
        return new ResponseEntity<>(carService.getCarById(carId), HttpStatus.OK);
    }

    /**
     * http://localhost:8081/cars/user/{userId}
     */
    @GetMapping("/user/{userId}")
    public Object getCarsByUserId(@PathVariable int userId){
        return ResponseEntity.ok(carService.getCarsByUser(userId));
    }

    /**
     * http://localhost:8081/cars/add-car
     */
    @PostMapping("/add-car")
    public Object addNewCar(@RequestBody Car car) {
        System.out.println("Incoming Car JSON:");
        System.out.println("Make: " + car.getMake());
        System.out.println("User ID: " + (car.getUser() != null ? car.getUser().getUserId() : "null"));

        Car savedCar = carService.addNewCar(car);
        return new ResponseEntity<>(savedCar, HttpStatus.CREATED);
    }

    /**
     * http://localhost:8081/cars/update/{carId}
     */
    @PutMapping("/update/{carId}")
    public Object updateCar(@PathVariable int carId, @RequestBody Car car) {
        Car existingCar = carService.getCarById(carId);
        if (existingCar == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found");
        }

        existingCar.setMake(car.getMake());
        existingCar.setModel(car.getModel());
        existingCar.setYear(car.getYear());
        existingCar.setColor(car.getColor());
        carService.addNewCar(existingCar);

        return ResponseEntity.ok(existingCar);
    }
}
