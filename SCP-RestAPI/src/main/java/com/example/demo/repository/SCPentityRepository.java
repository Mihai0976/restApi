package com.example.demo.repository;


import com.example.demo.model.SCPentity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SCPentityRepository extends MongoRepository<SCPentity, String> {
    Page<SCPentity> findByObjectClass(String objectClass, Pageable pageable);
}
