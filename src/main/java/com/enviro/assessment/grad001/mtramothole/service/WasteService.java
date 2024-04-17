package com.enviro.assessment.grad001.mtramothole.service;

import com.enviro.assessment.grad001.mtramothole.model.Waste;

import java.util.List;

// Interface for the WasteService class
// Exists to handle the CRUD operations for the Waste class, and to provide a way to handle exceptions and business logic away from the controller
// The WasteServiceImp class implements this interface
public interface WasteService {

    // Method to save a waste object. Returns the saved waste object.
    Waste saveWaste(Waste waste);

    // Method to remove a waste object by its id.
    void removeWasteById(Long id);

    // Method to update a waste object. Returns the updated waste object.
    Waste updateWaste(Waste waste);

    // Method to find a waste object by its id. Returns the found waste object.
    Waste findWasteById(Long id);

    // Method to remove waste objects by their category.
    void removeWasteListByWastecategory(String category);

    // Method to find waste objects by their category. Returns a list of found waste
    // objects.
    List<Waste> findWasteListByWastecategory(String category);

    // Method to find all waste objects. Returns a list of all waste objects.
    List<Waste> findAll();
}
