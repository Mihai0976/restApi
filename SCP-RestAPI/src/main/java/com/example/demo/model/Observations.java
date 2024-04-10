package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "observations")
public class Observations {
    @Id
    private String observationId;
    @Getter
    @Setter
    private String item;
    @Getter
    @Setter
    private String observation;
    @Getter
    @Setter
    private Date date;
    @Getter
    @Setter
    private String objectClass;

    public Object getId() {
        return observationId;
    }
}
