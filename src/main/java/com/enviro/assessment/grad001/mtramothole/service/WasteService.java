package com.enviro.assessment.grad001.mtramothole.service;

import com.enviro.assessment.grad001.mtramothole.model.Waste;

import java.util.List;

public interface WasteService {

    int saveWaste(Waste waste);

    int deleteWasteById(Long id);

    int updateWaste(Waste waste);

    Waste findWasteById(Long id);

    int deleteWasteByWastecategory(String wastecategory);

    int findWasteByWastecategory(String wastecategory);

    List<Waste> findAll();
}
