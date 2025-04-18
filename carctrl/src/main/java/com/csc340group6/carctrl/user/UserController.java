package com.csc340group6.carctrl.user;

import com.csc340group6.carctrl.user.User;
import com.csc340group6.carctrl.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    /**
    @Autowired
    private CarService carService;
    */

    /**
     * Get a list of users in the database.
     * http://localhost:8081/users/all
     * @return a list of User objects.
     */
    @GetMapping("/all")
    public Object getAllUser(){
        return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
    }

    /**
     * Get a User by id.
     * http://localhost:8081/users/{userId}
     * @param userId the User id
     * @return the User with given id.
     */
    @GetMapping("/{userId}")
    public Object getUser(@PathVariable int userId){
        return new ResponseEntity<>(service.getUserById(userId), HttpStatus.OK);
    }

    /**
     * Create a new User entry
     * http://localhost:8081/users/register
     * @param user the new User
     * @return the update list of Users.
     */
    @PostMapping("/register")
    public Object addNewUser(@RequestBody User user){
        if (user.getCars() != null) {
            user.getCars().forEach(car -> car.setUser(user));
        }
        System.out.println("Received user: " + user);
        service.addNewUser(user);
        return new ResponseEntity<>("New User Successfully Created!", HttpStatus.CREATED);
    }

    /**
     * Update an existing user.
     * http://localhost:8081/users/update/{userId}
     * @param userId the User id
     * @param user the new User details
     * @return the updated User object.
     */
    @PutMapping("/update/{userId}")
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
}
