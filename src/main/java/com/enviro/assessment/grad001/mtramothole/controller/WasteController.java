package com.enviro.assessment.grad001.mtramothole.controller;

import com.enviro.assessment.grad001.mtramothole.model.Waste;
import com.enviro.assessment.grad001.mtramothole.service.WasteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
        return wasteService.saveWaste(waste);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Waste> getWasteById(@PathVariable("id") Long wasteid) {
        return wasteService.findWasteById(wasteid);
    }

    @GetMapping(value = "/category/{category}")
    public ResponseEntity<List<Waste>> getWasteByCategory(@PathVariable("category") String category) {
        return wasteService.findWasteListByWastecategory(category);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Object> deleteWasteById(@PathVariable("id") Long wasteid) {
        return wasteService.removeWasteById(wasteid);
    }

    @DeleteMapping(value = "/delete/category/{category}")
    public ResponseEntity<Object> deleteWasteByCategory(@PathVariable("category") String category) {
        return wasteService.removeWasteListByWastecategory(category);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Waste> updateWaste(@Valid @RequestBody Waste waste) {
        return wasteService.updateWaste(waste);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Waste>> getAllWastes() {
        return wasteService.findAll();
    }
}