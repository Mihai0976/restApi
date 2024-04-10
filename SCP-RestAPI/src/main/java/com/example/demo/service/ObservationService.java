package com.example.demo.service;

import com.example.demo.model.Observations;
import com.example.demo.repository.ObservationRepository;
import com.example.demo.repository.SCPentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ObservationService {

    @Autowired
    private ObservationRepository repository;
    @Autowired
    private SCPentityRepository scPentityRepository;

    //Get all Observations from the database
    public List<Observations> findAll() {
        return repository.findAll();
    }

    //Get certain observation
    public Optional<Observations> findById(String id) {
        return repository.findById(id);
    }
    //Add Observation
    public Observations addObservation(Observations observation) {
        observation.setDate(new Date());
        return repository.save(observation);
    }

    //Delete Observation
    public boolean deleteObservationById(String observationId) {
        if (repository.existsById(observationId)) {
            repository.deleteById(observationId);
            return true;
        } else {
            return false;
        }

    }
}
