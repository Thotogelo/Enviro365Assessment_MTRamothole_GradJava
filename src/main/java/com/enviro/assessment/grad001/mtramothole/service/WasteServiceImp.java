package com.enviro.assessment.grad001.mtramothole.service;

import com.enviro.assessment.grad001.mtramothole.exception.WasteNotFoundException;
import com.enviro.assessment.grad001.mtramothole.model.Waste;
import com.enviro.assessment.grad001.mtramothole.repository.WasteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class WasteServiceImp implements WasteService {

    private final WasteRepository wasteRepository;

    public WasteServiceImp(WasteRepository wasteRepository) {
        this.wasteRepository = wasteRepository;
    }

    // Method to save a waste object. Returns the saved waste object.
    @Override
    @Transactional
    public Waste saveWaste(Waste waste) {
        return wasteRepository.save(waste);
    }

    // Save can be used to update as well
    // Method to update a waste object. Returns the updated waste object.
    @Override
    @Transactional
    public Waste updateWaste(Waste waste) {
        return wasteRepository.save(waste);
    }

    // Method to find a waste object by its id. Returns the found waste object.
    // exception is thrown if waste is not found and handled in the global exception handler
    @Override
    public Waste findWasteById(Long id) {
        Optional<Waste> waste = wasteRepository.findById(id);
        return waste.orElseThrow(() -> new WasteNotFoundException("Waste with id " + id + " not found."));
    }

    // Method to remove a waste object by its id.
    // exception is thrown if waste is not found and handled in the global exception handler
    @Override
    @Transactional
    public void removeWasteById(Long id) {
        int rowsAffected = wasteRepository.deleteWasteById(id);
        if (rowsAffected == 0) {
            throw new WasteNotFoundException("Waste with id " + id + " not found.");
        }
    }

    // Method to remove waste objects by their category.
    // exception is thrown if waste is not found and handled in the global exception handler
    @Override
    @Transactional
    public void removeWasteListByWastecategory(String category) {
        int rowsAffected = wasteRepository.deleteWasteByWastecategory(category);
        if (rowsAffected == 0) {
            throw new WasteNotFoundException("Waste list with category " + category + " not found.");
        }
    }

    // Method to find waste objects by their category. Returns a list of found waste objects.
    // exception is thrown if waste is not found and handled in the global exception handler
    @Override
    public List<Waste> findWasteListByWastecategory(String category) {
        List<Waste> wasteList = wasteRepository.findWasteByWastecategory(category);
        if (wasteList != null) {
            return wasteList;
        } else {
            throw new WasteNotFoundException("Waste lists with category " + category + " not found.");
        }
    }

    // Method to find all waste objects. Returns a list of all waste objects.
    // exception is thrown if waste is not found and handled in the global exception handler
    @Override
    public List<Waste> findAll() {
        return wasteRepository.findAll();
    }
}