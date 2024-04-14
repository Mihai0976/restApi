package com.example.demo.repository;

import com.example.demo.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository <Users, String> {
    Optional<Users> findByUsername(String username);

    Boolean existsByUsername(String username);
}