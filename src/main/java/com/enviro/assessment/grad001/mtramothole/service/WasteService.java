package com.enviro.assessment.grad001.mtramothole.service;

import com.enviro.assessment.grad001.mtramothole.model.Waste;

import java.util.List;

public interface WasteService {

    Waste saveWaste(Waste waste);

    void removeWasteById(Long id);

    Waste updateWaste(Waste waste);

    Waste findWasteById(Long id);

    void removeWasteByWastecategory(String category);

    List<Waste> findWasteByWastecategory(String category);

    List<Waste> findAll();
}
