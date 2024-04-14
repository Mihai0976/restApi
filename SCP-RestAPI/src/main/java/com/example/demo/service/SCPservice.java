package com.example.demo.service;

import com.example.demo.model.SCPentity;
import com.example.demo.repository.SCPentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class SCPservice {

    @Autowired
    private SCPentityRepository repository;

    // Retrieve all anomalies from the database
    public Page<SCPentity> getAllAnomalies(Pageable pageable) {
        return repository.findAll(pageable);
    }

    // Retrieve a specific anomaly by its ID
    public Optional<SCPentity> getAnomalyById(String id) {
        return repository.findById(id);
    }

    // Add a new anomaly to the database
    public SCPentity addAnomaly(SCPentity anomaly) {
        return repository.save(anomaly);
    }

    // Add observations to an existing anomaly's description
    public SCPentity addDescription(String anomalyId, String description) {
        SCPentity anomaly = repository.findById(anomalyId)
                .orElseThrow(() -> new RuntimeException("Anomaly not found with id: " + anomalyId));
        anomaly.setDescription(anomaly.getDescription() + "\n" + description);
        return repository.save(anomaly);
    }

    // Delete an anomaly by its ID
    public boolean deleteAnomalyById(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true; // Anomaly successfully deleted
        } else {
            return false; // Anomaly with the specified ID not found
        }
    }
    public Page<SCPentity> getAnomaliesByObjectClass(String objectClass, Pageable pageable) {
        return repository.findByObjectClass(objectClass, pageable);
    }

}