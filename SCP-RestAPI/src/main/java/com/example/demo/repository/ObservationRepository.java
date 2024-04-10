package com.example.demo.repository;

import com.example.demo.model.Observations;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ObservationRepository extends MongoRepository<Observations, String> {

}
