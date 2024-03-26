package com.example.demo.repository;


import com.example.demo.model.SCPentity;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface SCPentityRepository extends MongoRepository<SCPentity, String> {

}
