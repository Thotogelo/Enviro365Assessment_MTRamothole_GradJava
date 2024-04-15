package com.enviro.assessment.grad001.mtramothole.service;

import com.enviro.assessment.grad001.mtramothole.model.Waste;
import com.enviro.assessment.grad001.mtramothole.repository.WasteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WasteServiceImp implements WasteService {

    private final WasteRepository wasteRepository;

    public WasteServiceImp(WasteRepository wasteRepository) {
        this.wasteRepository = wasteRepository;
    }

    @Override
    public int saveWaste(Waste waste) {
        return wasteRepository.saveWaste(waste);
    }

    @Override
    public int deleteWasteById(Long id) {
        return wasteRepository.deleteWasteById(id);
    }

    @Override
    public int updateWaste(Waste waste) {
        return wasteRepository.updateWaste(waste);
    }

    @Override
    public Waste findWasteById(Long id) {
        return wasteRepository.findWasteById(id);
    }

    @Override
    public int deleteWasteByWastecategory(String wastecategory) {
        return wasteRepository.deleteWasteByWastecategory(wastecategory);
    }

    @Override
    public int findWasteByWastecategory(String wastecategory) {
        return wasteRepository.findWasteByWastecategory(wastecategory);
    }

    public List<Waste> findAll() {
        return wasteRepository.findAll();
    }
}