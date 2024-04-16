package com.enviro.assessment.grad001.mtramothole.repository;

import com.enviro.assessment.grad001.mtramothole.model.Waste;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WasteRepository extends CrudRepository<Waste, Long> {

    public int deleteWasteById(Long id);

    public Waste findWasteById(Long id);

    public int deleteWasteByWastecategory(String wastecategory);

    public List<Waste> findWasteByWastecategory(String wastecategory);

    @Override
    public List<Waste> findAll();
}