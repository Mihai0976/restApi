package com.example.demo.service;

import com.example.demo.model.Users;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    //Lets a user register an account (automatically gets the role PLEB by registering via this endpoint)
    public Users addUser(Users user) {
        user.setRole("PLEB");
        return  repository.save(user);
    }
    public Users adminAddUser(Users user) {
        return repository.save(user);
    }
    // Method to retrieve paginated users
    public Page<Users> getAllUsers(Pageable pageable) {return repository.findAll(pageable);}

}
