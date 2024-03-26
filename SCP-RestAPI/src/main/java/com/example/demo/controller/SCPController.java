package com.example.demo.controller;


import com.example.demo.model.SCPentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//Basically en router som sköter inkommande HTTP förfrågningar
@RestController
@RequestMapping("/api")
public class SCPController {

    @GetMapping(path = "/hello") //Surfar man in på http://localhost:8080/hello kommer "SCP Anomaly database" displayas i browsern
    public String helloSCP() {
        return "hello";
    }


    //To Do: Skapa nödvändiga mappings
}
