package com.example.demo.controller;

import com.example.demo.model.Observations;
import com.example.demo.service.ObservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ObservationController {

    @Autowired
    private ObservationService observationService;

    @GetMapping("/observations")
    public ResponseEntity<List<Observations>> getAllObservations() {
        List<Observations> observations = observationService.findAll();
        return new ResponseEntity<>(observations, HttpStatus.OK);
    }

    @PostMapping("/addObservation")
    public ResponseEntity<Observations> addObservation(@RequestBody Observations observations) {
        Observations newObservation = observationService.addObservation(observations);
        return ResponseEntity.status(HttpStatus.CREATED).body(newObservation);
    }
}
