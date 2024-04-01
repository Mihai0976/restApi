package com.example.demo.service;

import com.example.demo.model.SCPentity;
import com.example.demo.repository.SCPentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
//
//import java.util.UUID;

//@Service
//public class SCPservice {
//
//    @Autowired
//    private SCPentityRepository repository;
//
//    //TO DO: IMPLEMENT CRUD CREATE READ DELETE UPDATE
//
//    public SCPentity addEntity(@NotNull SCPentity scPentity, String item, String name, String objectClass, String description) {
//        scPentity.setId(UUID.randomUUID().toString().split("-")[0]); //Genererar random UUID och konverterar till string
//        scPentity.setItem(item);
//        scPentity.setName(name);
//        scPentity.setObjectClass(objectClass);
//        scPentity.setDescription(description);
//        return repository.save(scPentity);
//    }
//
//}
@Service
public class SCPservice {

    @Autowired
    private SCPentityRepository repository;

    // Retrieve all anomalies from the database
    public List<SCPentity> getAllAnomalies() {
        return repository.findAll();
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
    public SCPentity addObservations(String anomalyId, String observations) {
        SCPentity anomaly = repository.findById(anomalyId)
                .orElseThrow(() -> new RuntimeException("Anomaly not found with id: " + anomalyId));
        anomaly.setDescription(anomaly.getDescription() + "\n" + observations);
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
}
