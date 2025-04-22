package com.csc340group6.carctrl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Fetch all Users.
     * @return the list of all Users.
     */
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    /**
     * Fetch a unique User.
     * @param userId a User's id
     * @return a unique User.
     */
    public User getUserById(int userId){
        return userRepository.findById(userId).orElse(null);
    }


    /**
     * Fetch a User by username.
     * @param username a username
     * @return a User by that username.
     */
    public User getByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    /**
     * Add a new User.
     * @param user a User to add
     */
    public void addNewUser(User user){
        userRepository.save(user);
    }

    /**
     * Update User information.
     * @param user
     */
    public void updateUser(User user){
        userRepository.save(user);
    }

    /**
     * Delete a unique User.
     * @param userId the unique User id
     */
    public void deleteUserById(int userId){
        userRepository.deleteById(userId);
    }

}
