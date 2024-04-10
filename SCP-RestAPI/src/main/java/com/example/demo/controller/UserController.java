package com.example.demo.controller;

import com.example.demo.util.AuthRequest;
import com.example.demo.service.JwtService;
import com.example.demo.model.Users;
import com.example.demo.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    // Endpoint to add a new user
    @PostMapping("/register")
    public ResponseEntity<Users> addUser(@RequestBody Users user) {
        Users savedUser = userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
    // Endpoint for admins to add a new user (They can choose roles)
    @PostMapping("/adminreg")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Users> adminAddUser(@Valid @RequestBody Users user) {
        Users savedUser = userService.adminAddUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
    // Endpoint to retrieve all users
    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Page<Users>> getAllUsers(@Valid
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Users> usersPage = userService.getAllUsers(pageable);
        return ResponseEntity.ok(usersPage);
    }
    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
}
