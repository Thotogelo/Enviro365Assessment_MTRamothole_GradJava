package com.enviro.assessment.grad001.mtramothole.controller;

import com.enviro.assessment.grad001.mtramothole.model.Waste;
import com.enviro.assessment.grad001.mtramothole.service.WasteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/api/waste")
@Tag(name = "Waste API")
public class WasteController {

    private final WasteService wasteService;

    public WasteController(WasteService wasteService) {
        this.wasteService = wasteService;
    }

    @PostMapping(value = "/save")
    @Operation(summary = "Save a waste")
    public ResponseEntity<Waste> saveWaste(@Valid @RequestBody Waste waste) {
        return ResponseEntity.ok(wasteService.saveWaste(waste));
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Get a waste by id")
    public ResponseEntity<Waste> getWasteById(@PathVariable("id") Long wasteid) {
        Waste waste = wasteService.findWasteById(wasteid);
        return (waste != null) ? ResponseEntity.ok(waste) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/category/{category}")
    @Operation(summary = "Get a waste list by category")
    public ResponseEntity<Iterable<Waste>> getWasteByCategory(@PathVariable("category") String category) {
        Iterable<Waste> waste = wasteService.findWasteListByWastecategory(category);
        return (waste != null) ? ResponseEntity.ok(waste) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary = "Delete a waste by id")
    public ResponseEntity<String> deleteWasteById(@PathVariable("id") Long wasteid) {
        wasteService.removeWasteById(wasteid);
        return ResponseEntity.ok("Waste successfully deleted.");
    }

    @DeleteMapping(value = "/delete/category/{category}")
    @Operation(summary = "Delete a waste list by category")
    public ResponseEntity<String> deleteWasteByCategory(@PathVariable("category") String category) {
        wasteService.removeWasteListByWastecategory(category);
        return ResponseEntity.ok("Waste list successfully deleted.");
    }

    @PutMapping(value = "/update")
    @Operation(summary = "Update a waste")
    public ResponseEntity<Waste> updateWaste(@Valid @RequestBody Waste waste) {
        return ResponseEntity.ok(wasteService.updateWaste(waste));
    }

    @GetMapping(value = "/all")
    @Operation(summary = "Get all waste data")
    public ResponseEntity<Iterable<Waste>> getAllWastes() {
        return ResponseEntity.ok(wasteService.findAll());
    }
}