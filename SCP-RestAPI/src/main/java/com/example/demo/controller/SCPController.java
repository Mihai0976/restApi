package com.example.demo.controller;


import com.example.demo.model.SCPentity;
import com.example.demo.service.SCPservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//Basically en router som sköter inkommande HTTP förfrågningar
@RestController
@RequestMapping("/api/anomalies")
public class SCPController {

    @GetMapping(path = "/hello") //Surfar man in på http://localhost:8080/hello kommer "SCP Anomaly database" displayas i browsern
    public String helloSCP() {
        return "hello";
    }

    @Autowired
    private SCPservice anomalyService;

    @GetMapping
    public ResponseEntity<List<SCPentity>> getAllAnomalies() {
        List<SCPentity> anomalies = anomalyService.getAllAnomalies();
        return ResponseEntity.ok(anomalies);
    }


    //To Do: Skapa nödvändiga mappings
}
