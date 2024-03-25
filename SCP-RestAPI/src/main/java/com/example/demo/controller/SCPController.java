package com.example.demo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


//Basically en router som sköter inkommande HTTP förfrågningar
@RestController
public class SCPController {
    @GetMapping(path = "/hello") //Surfar man in på http://localhost:8080/hello kommer "SCP Anomaly database" displayas i browsern
    public String helloSCP() {
        return "hello";
    }

    //To Do: Skapa nödvändiga mappings
}
