package com.enviro.assessment.grad001.mtramothole.service;

import com.enviro.assessment.grad001.mtramothole.model.Waste;
import com.enviro.assessment.grad001.mtramothole.repository.WasteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
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

    public ResponseEntity<Waste> saveWaste(Waste waste) {
        if (wasteRepository.existsById(waste.getId()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(wasteRepository.save(waste), HttpStatus.CREATED);
    }

    public ResponseEntity<Waste> updateWaste(Waste updateWaste) {

        if (!wasteRepository.existsById(updateWaste.getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Waste storedWaste = wasteRepository.findById(updateWaste.getId()).get();
        storedWaste.setWastecategory(updateWaste.getWastecategory());
        storedWaste.setDisposalguideline(updateWaste.getDisposalguideline());
        storedWaste.setRecyclingtip(updateWaste.getRecyclingtip());
        return new ResponseEntity<>(wasteRepository.save(storedWaste), HttpStatus.CREATED);
    }

    public ResponseEntity<Waste> findWasteById(Long id) {
        Optional<Waste> waste = wasteRepository.findById(id);
        return waste.map(value -> new ResponseEntity<>(value, HttpStatus.FOUND)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Object> removeWasteById(Long id) {
        if (!wasteRepository.existsById(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        wasteRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Object> removeWasteListByWastecategory(String category) {
        LinkedList<Waste> wasteList = (LinkedList<Waste>) wasteRepository.findWasteByWastecategory(category);

        if (wasteList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        wasteRepository.deleteAll(wasteRepository.findWasteByWastecategory(category.toLowerCase()));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Iterable<Waste>> findWasteListByWastecategory(String category) {
        return ResponseEntity.ok(wasteRepository.findWasteByWastecategory(category));
    }

    public ResponseEntity<Iterable<Waste>> findAll() {
        return ResponseEntity.ok(wasteRepository.findAll());
    }
}