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

    public Waste updateWaste(Waste updateWaste) {
        Waste storedWaste = findWasteById(updateWaste.getId());
        storedWaste.setWastecategory(updateWaste.getWastecategory());
        storedWaste.setDisposalguideline(updateWaste.getDisposalguideline());
        storedWaste.setRecyclingtip(updateWaste.getRecyclingtip());
        return wasteRepository.save(storedWaste);
    }

    public Waste findWasteById(Long id) {
        Optional<Waste> storedWaste = wasteRepository.findById(id);
        return storedWaste.orElseThrow(() -> new WasteNotFoundException("Waste with id " + id + " not found."));
    }

    public void removeWasteById(Long id) {
        Waste storedWaste = findWasteById(id);
        wasteRepository.deleteById(storedWaste.getId());
    }

    public void removeWasteListByWastecategory(String category) {
        Iterable<Waste> existsWasteList = findWasteListByWastecategory(category.toLowerCase());
        wasteRepository.deleteAll(existsWasteList);
    }

    public Iterable<Waste> findWasteListByWastecategory(String category) {
        return wasteRepository.findWasteByWastecategory(category);
    }

    public Iterable<Waste> findAll() {
        return wasteRepository.findAll();
    }
}