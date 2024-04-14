package com.example.demo.controller;
import com.example.demo.model.SCPentity;
import com.example.demo.service.SCPservice;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



//if you access http://localhost:8080/swagger-ui/index.html#/scp-controller you get the swagger gui
@RestController
@RequestMapping("/api")
public class SCPController {

    @Autowired
    private SCPservice anomalyService;

    // Endpoint to retrieve all anomalies
    @GetMapping("/anomalies")
    public ResponseEntity<EntityModel<Page<SCPentity>>> getAllAnomalies(@RequestParam(defaultValue = "0") int page,
                                                                        @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<SCPentity> anomalies = anomalyService.getAllAnomalies(pageable);

        EntityModel<Page<SCPentity>> model = EntityModel.of(anomalies);
        if (!anomalies.isEmpty()) {
            int prevPage = anomalies.hasPrevious() ? anomalies.getNumber() - 1 : 0;
            int nextPage = anomalies.hasNext() ? anomalies.getNumber() + 1 : anomalies.getNumber();

            model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SCPController.class)
                            .getAllAnomalies(prevPage, size))
                    .withRel("prev"));

            model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SCPController.class)
                            .getAllAnomalies(nextPage, size))
                    .withRel("next"));
        }

        return ResponseEntity.ok(model);
    }

    // Endpoint to retrieve a specific anomaly by ID
    @GetMapping("/anomalies/{id}")
    public ResponseEntity<SCPentity> getAnomalieById(@PathVariable String id) {
        return anomalyService.getAnomalyById(id)
                .map(anomaly -> ResponseEntity.ok().body(anomaly))
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint to add a new anomaly
    @Operation(summary = "Add Anomaly", description = "Endpoint to add a new anomaly")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/anomalies")
    public ResponseEntity<SCPentity> addAnomaliee(@RequestBody SCPentity anomaly) {
        SCPentity savedAnomaly = anomalyService.addAnomaly(anomaly);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAnomaly);
    }

    // Endpoint to update anomaly's description by adding new observations
    @Operation(summary = "Update anomaly", description = "Endpoint to update a specific anomaly")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/anomalies/{anomalyId}/observations")
    public ResponseEntity<SCPentity> updateAnomalieDescription(@PathVariable String anomalyId, @RequestBody String observations) {
        SCPentity updatedScpentity = anomalyService.addDescription(anomalyId, observations);
        return ResponseEntity.status(HttpStatus.OK).body(updatedScpentity);
    }

    // Endpoint to delete a specific anomaly by ID
    @Operation(summary = "Delete Anomaly", description = "Endpoint to delete a specific anomaly")
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/anomalies/{id}")
    public ResponseEntity<Void> deleteAnomalieById(@PathVariable String id) {
        boolean deleted = anomalyService.deleteAnomalyById(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // Anomaly successfully deleted
        } else {
            return ResponseEntity.notFound().build(); // Anomaly with the specified ID not found
        }
    }

    @GetMapping("/anomalies/object-class/{objectClass}")
    public ResponseEntity<EntityModel<Page<SCPentity>>> getAnomaliesByObjectClass(@PathVariable String objectClass,
                                                                                  @RequestParam(defaultValue = "0") int page,
                                                                                  @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<SCPentity> anomalies = anomalyService.getAnomaliesByObjectClass(objectClass, pageable);

        EntityModel<Page<SCPentity>> model = EntityModel.of(anomalies);
        if (!anomalies.isEmpty()) {
            int prevPage = anomalies.hasPrevious() ? anomalies.getNumber() - 1 : 0;
            int nextPage = anomalies.hasNext() ? anomalies.getNumber() + 1 : anomalies.getNumber();

            model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SCPController.class)
                            .getAnomaliesByObjectClass(objectClass, prevPage, size))
                    .withRel("prev"));

            model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SCPController.class)
                            .getAnomaliesByObjectClass(objectClass, nextPage, size))
                    .withRel("next"));
        }

        return ResponseEntity.ok(model);
    }
}
