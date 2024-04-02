package com.example.demo.repository;

import com.example.demo.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository <Users, String> {
    Page<Users> findByRole(Users.UserRole role, Pageable pageable);
}
