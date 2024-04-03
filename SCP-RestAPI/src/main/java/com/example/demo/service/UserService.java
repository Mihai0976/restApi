package com.example.demo.service;

import com.example.demo.model.Users;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    //Lets a user register an account (automatically gets the role PLEB by registering via this endpoint)
    public Users addUser(Users user) {
        user.setRole(Users.UserRole.PLEB);
        return  repository.save(user);
    }

}
