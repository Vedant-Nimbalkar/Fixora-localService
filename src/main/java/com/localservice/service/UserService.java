package com.localservice.service;

import com.localservice.model.User;
import com.localservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void registerUser(User user){

        user.setRole("USER");
        userRepository.save(user);

    }
    
    public User login(String email, String password){

        User user = userRepository.findByEmail(email);

        if(user != null && user.getPassword().equals(password)){
            return user;
        }

        return null;
    }
}