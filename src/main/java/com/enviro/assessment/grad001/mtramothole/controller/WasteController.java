package com.enviro.assessment.grad001.mtramothole.controller;

import com.enviro.assessment.grad001.mtramothole.model.Waste;
import com.enviro.assessment.grad001.mtramothole.service.WasteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/waste")
@Tag(name = "Waste API", description = "Get waste data")
public class WasteController {

    private final WasteService wasteService;
    private static final Logger logger = LoggerFactory.getLogger(WasteController.class);
    private static final String errorOccuredMessage = "An error occurred while processing the request.";

    public WasteController(WasteService wasteService) {
        this.wasteService = wasteService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveWaste(@Valid @RequestBody Waste waste) {
        try {
            int rowsAffected = wasteService.saveWaste(waste);
            return (rowsAffected > 0) ? new ResponseEntity<>("Waste successfully saved.", HttpStatus.CREATED) :
                    new ResponseEntity<>("Failed to save waste.", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            logger.error("Error occurred while saving waste", e);
            return new ResponseEntity<>(errorOccuredMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find/{wasteid}")
    public ResponseEntity<Waste> findWaste(@PathVariable("wasteid") Long wasteid) {
        try {
            Waste waste = wasteService.findWasteById(wasteid);
            return (waste != null) ? new ResponseEntity<>(waste, HttpStatus.OK) :
                    new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error occurred while finding waste", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{wasteid}")
    public ResponseEntity<String> deleteWaste(@PathVariable("wasteid") Long wasteid) {
        try {
            int rowsAffected = wasteService.deleteWasteById(wasteid);
            return (rowsAffected > 0) ? new ResponseEntity<>("Waste successfully deleted.", HttpStatus.OK) :
                    new ResponseEntity<>("Failed to delete waste.", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            logger.error("Error occurred while deleting waste", e);
            return new ResponseEntity<>(errorOccuredMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateWaste(@Valid @RequestBody Waste waste) {
        try {
            int rowsAffected = wasteService.updateWaste(waste);
            return (rowsAffected > 0) ? new ResponseEntity<>("Waste successfully updated.", HttpStatus.OK) :
                    new ResponseEntity<>("Failed to update waste.", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            logger.error("Error occurred while updating waste", e);
            return new ResponseEntity<>(errorOccuredMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}