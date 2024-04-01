package com.example.demo.controller;


import com.example.demo.model.SCPentity;
import com.example.demo.service.SCPservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//if you access http://localhost:8080/swagger-ui/index.html#/scp-controller you get the swagger gui
@RestController
@RequestMapping("/api")
public class SCPController {

    @GetMapping(path = "/hello") //Surfar man in på http://localhost:8080/hello kommer "SCP Anomaly database" displayas i browsern
    public String helloSCP() {
        return "hello";
    }

    @Autowired
    private SCPservice anomalyService;

    //  // Endpoint to retrieve all anomalies
    @GetMapping("/anomalies")
    public ResponseEntity<List<SCPentity>> getAllAnomalies() {
        List<SCPentity> anomalies = anomalyService.getAllAnomalies();
        return ResponseEntity.ok(anomalies);
    }

    // Endpoint to retrieve a specific anomaly by ID
    @GetMapping("/anomalies/{id}")
    public ResponseEntity<SCPentity>getAnomalieById(@PathVariable String id){
        return  anomalyService.getAnomalyById(id)
                .map(anomaly -> ResponseEntity.ok().body(anomaly))
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint to add a new anomaly
    @PostMapping("/anomalies")
    public ResponseEntity<SCPentity>addAnomaliee(@RequestBody SCPentity anomaly){
        SCPentity savedAnomaly = anomalyService.addAnomaly(anomaly);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAnomaly);
    }

    // Endpoint to update anomaly's description by adding new observations
    @PutMapping("/anomalies/{anomalyId}/observations")
    public ResponseEntity<SCPentity>updateAnomalieDescription(@PathVariable String anomalyId, @RequestBody String observations){
        SCPentity updatedScpentity = anomalyService.addObservations(anomalyId, observations);
        return ResponseEntity.status(HttpStatus.OK).body(updatedScpentity);
    }

    // Endpoint to delete a specific anomaly by ID
    @DeleteMapping("/anomalies/{id}")
    public ResponseEntity<Void> deleteAnomalieById(@PathVariable String id) {
        boolean deleted = anomalyService.deleteAnomalyById(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // Anomaly successfully deleted
        } else {
            return ResponseEntity.notFound().build(); // Anomaly with the specified ID not found
        }
    }

}
