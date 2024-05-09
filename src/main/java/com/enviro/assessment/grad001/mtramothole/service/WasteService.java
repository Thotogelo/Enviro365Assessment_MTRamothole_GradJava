package com.enviro.assessment.grad001.mtramothole.service;

import com.enviro.assessment.grad001.mtramothole.exception.WasteNotFoundException;
import com.enviro.assessment.grad001.mtramothole.model.Waste;
import com.enviro.assessment.grad001.mtramothole.repository.WasteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/*Provided a service class that will be used to interact with the WasteRepository and Controller
Separation of concerns is achieved by having the service class handle the business logic
 And the repository class handle the database operations
*/
@Service
public class WasteService {

    private final WasteRepository wasteRepository;

    public WasteService(WasteRepository wasteRepository) {
        this.wasteRepository = wasteRepository;
    }

    public Waste saveWaste(Waste waste) {
        return wasteRepository.save(waste);
    }

    public Waste updateWaste(Waste waste) {
        return wasteRepository.save(waste);
    }

    public Waste findWasteById(Long id) {
        Optional<Waste> waste = wasteRepository.findById(id);
        return waste.orElseThrow(() -> new WasteNotFoundException("Waste with id " + id + " not found."));
    }

    public void removeWasteById(Long id) {
        int rowsAffected = wasteRepository.deleteWasteById(id);
        if (rowsAffected == 0) {
            throw new WasteNotFoundException("Waste with id " + id + " not found.");
        }
    }

    public void removeWasteListByWastecategory(String category) {
        int rowsAffected = wasteRepository.deleteWasteByWastecategory(category);
        if (rowsAffected == 0) {
            throw new WasteNotFoundException("Waste list with category " + category + " not found.");
        }
    }

    public Iterable<Waste> findWasteListByWastecategory(String category) {
        Iterable<Waste> wasteList = wasteRepository.findWasteByWastecategory(category);
        if (wasteList != null) {
            return wasteList;
        } else {
            throw new WasteNotFoundException("Waste lists with category " + category + " not found.");
        }
    }

    public Iterable<Waste> findAll() {
        return wasteRepository.findAll();
    }
}