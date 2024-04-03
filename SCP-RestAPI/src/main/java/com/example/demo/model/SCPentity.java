package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "entities")
@AllArgsConstructor
public class SCPentity {
    @Id
    private String id;
    @Getter
    @Setter
    private String item; //Anomaliernas SCP item kod (t.ex SCP-002 för the "Living" room
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String objectClass;
    @Setter
    @Getter
    private String description;

    // Constructor with parameters
    public SCPentity(String item, String name, String objectClass, String description) {
        this.item = item;
        this.name = name;
        this.objectClass = objectClass;
        this.description = description;
    }

    // No-args constructor
    public SCPentity() {
    }

}
