package com.example.demo.repository;


import com.example.demo.model.SCPentity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface SCPentityRepository extends MongoRepository<SCPentity, String> {
    List<SCPentity> findByObjectClass(String objectClass);
}
