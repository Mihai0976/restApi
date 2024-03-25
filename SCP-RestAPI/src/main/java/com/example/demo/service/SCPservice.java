package com.example.demo.service;

import com.example.demo.model.SCPentity;
import com.example.demo.repository.SCPentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SCPservice {

    @Autowired
    private SCPentityRepository repository;

    //TO DO: IMPLEMENT CRUD CREATE READ DELETE UPDATE

    public SCPentity addEntity(SCPentity scPentity) {
        scPentity.setId(UUID.randomUUID().toString().split("-")[0]); //Genererar random UUID och konverterar till string
        return repository.save(scPentity);
    }

}
