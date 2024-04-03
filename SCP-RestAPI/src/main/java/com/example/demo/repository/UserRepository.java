package com.example.demo.repository;

import com.example.demo.model.Users;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository <Users, String> {
    @NotNull Page<Users> findAll(@NotNull Pageable pageable);
}
