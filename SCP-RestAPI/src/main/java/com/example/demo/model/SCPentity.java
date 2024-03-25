package com.example.demo.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "entities")
@Data
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
    private String objectClass; //Euclid, Keter, Safe, Keter, Thaumiel, Neutralized, Decommissioned, Apollyon, Archon
    @Setter
    @Getter
    private String description;

}
