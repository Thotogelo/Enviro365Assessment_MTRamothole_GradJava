package com.enviro.assessment.grad001.mtramothole.controller;

import com.enviro.assessment.grad001.mtramothole.model.Waste;
import com.enviro.assessment.grad001.mtramothole.service.WasteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Waste> saveWaste(@Valid @RequestBody Waste waste) {
        Waste storedWaste = wasteService.saveWaste(waste);
        return ResponseEntity.status(HttpStatus.CREATED).body(storedWaste);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Waste> getWasteById(@PathVariable("id") Long wasteid) {
        Waste waste = wasteService.findWasteById(wasteid);
        return (waste != null) ? ResponseEntity.ok(waste) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/category/{category}")
    public ResponseEntity<Iterable<Waste>> getWasteByCategory(@PathVariable("category") String category) {
        Iterable<Waste> waste = wasteService.findWasteListByWastecategory(category);
        return (waste != null) ? ResponseEntity.ok(waste) : ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteWasteById(@PathVariable("id") Long wasteid) {
        wasteService.removeWasteById(wasteid);
        return ResponseEntity.ok("Waste removed.");
    }

    @DeleteMapping(value = "/delete/category/{category}")
    public ResponseEntity<String> deleteWasteByCategory(@PathVariable("category") String category) {
        wasteService.removeWasteListByWastecategory(category);
        return ResponseEntity.ok("Waste list deleted.");
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Waste> updateWaste(@Valid @RequestBody Waste waste) {
        Waste updateWaste = wasteService.updateWaste(waste);
        return ResponseEntity.ok(updateWaste);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<Iterable<Waste>> getAllWastes() {
        return ResponseEntity.ok(wasteService.findAll());
    }
}