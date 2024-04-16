package com.enviro.assessment.grad001.mtramothole.controller;

import com.enviro.assessment.grad001.mtramothole.model.Waste;
import com.enviro.assessment.grad001.mtramothole.service.WasteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/waste")
@Tag(name = "Waste API", description = "Retrieve, Remove, Save, and Update waste data")
public class WasteController {

    private final WasteService wasteService;
    public WasteController(WasteService wasteService) {
        this.wasteService = wasteService;
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Save a waste", description = "Save a waste data")
    public ResponseEntity<Waste> saveWaste(@Valid @RequestBody Waste waste) {
        return ResponseEntity.ok(wasteService.saveWaste(waste));
    }

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a waste by id", description = "Returns a waste by id")
    public ResponseEntity<Waste> getWasteById(@PathVariable("id") Long wasteid) {
        Waste waste = wasteService.findWasteById(wasteid);
        return (waste != null) ? ResponseEntity.ok(waste) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/category/{category}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a waste by category", description = "Returns a waste by category")
    public ResponseEntity<List<Waste>> getWasteByCategory(@PathVariable("category") String wastecategory) {
        List<Waste> waste = wasteService.findWasteByWastecategory(wastecategory);
        return (waste != null) ? ResponseEntity.ok(waste) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Delete a waste by id", description = "Delete a waste by id")
    public ResponseEntity<String> deleteWasteById(@PathVariable("id") Long wasteid) {
        wasteService.removeWasteById(wasteid);
        return ResponseEntity.ok("Waste successfully deleted.");
    }

    @DeleteMapping(value = "/delete/category/{category}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Delete a waste by category", description = "Delete a waste by category")
    public ResponseEntity<String> deleteWasteByCategory(@PathVariable("category") String category) {
        wasteService.removeWasteByWastecategory(category);
        return ResponseEntity.ok("Waste successfully deleted.");
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update a waste", description = "Update a waste data")
    public ResponseEntity<Waste> updateWaste(@Valid @RequestBody Waste waste) {
        return ResponseEntity.ok(wasteService.updateWaste(waste));
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all waste", description = "Returns all waste data")
    public ResponseEntity<List<Waste>> getAllWastes() {
        return ResponseEntity.ok(wasteService.findAll());
    }
}