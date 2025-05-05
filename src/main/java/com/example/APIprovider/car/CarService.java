package com.example.APIprovider.car;

//import com.csc340group6.carctrl.user.User;
//import com.csc340group6.carctrl.user.UserRepository;
import com.example.APIprovider.user.User;
import com.example.APIprovider.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarService {

    @Autowired
    public CarRepository carRepository;

    @Autowired
    public UserRepository userRepository;

    public List<Car> getAllCars(){
        return carRepository.findAll();
    }

    public Car getCarById(int carId){
        return carRepository.findById(carId).orElse(null);
    }

    public Car addNewCar(Car car){
        if (car.getUser() == null) {
            throw new RuntimeException("User ID must be provided to assign car.");
        }
        User user = userRepository.findById(car.getUser().getUserId()).orElseThrow(() -> new RuntimeException("User not found with ID: " + car.getUser().getUserId()));

        car.setUser(user);
        carRepository.save(car);
        return car;
    }

    public List<Car> getCarsByUser(int userId){
        return carRepository.findCarByUser(userId);
    }
}
