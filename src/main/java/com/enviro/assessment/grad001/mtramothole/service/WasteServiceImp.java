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

    @Override
    @Transactional
    public Waste saveWaste(Waste waste) {
        return wasteRepository.save(waste);
    }

    @Override
    @Transactional
    public Waste updateWaste(Waste waste) {
        return wasteRepository.save(waste);
    }

    @Override
    public Waste findWasteById(Long id) {
        Optional<Waste> waste = wasteRepository.findById(id);
        return waste.orElseThrow(() -> new WasteNotFoundException("Waste with id " + id + " not found."));
    }

    @Override
    @Transactional
    public void removeWasteById(Long id) {
        int rowsAffected = wasteRepository.deleteWasteById(id);
        if (rowsAffected == 0) {
            throw new WasteNotFoundException("Waste with id " + id + " not found.");
        }
    }

    @Override
    @Transactional
    public void removeWasteListByWastecategory(String category) {
        int rowsAffected = wasteRepository.deleteWasteByWastecategory(category);
        if (rowsAffected == 0) {
            throw new WasteNotFoundException("Waste list with category " + category + " not found.");
        }
    }

    @Override
    public List<Waste> findWasteListByWastecategory(String category) {
        List<Waste> wasteList = wasteRepository.findWasteByWastecategory(category);
        if (wasteList != null) {
            return wasteList;
        } else {
            throw new WasteNotFoundException("Waste lists with category " + category + " not found.");
        }
    }

    @Override
    public List<Waste> findAll() {
        return wasteRepository.findAll();
    }
}