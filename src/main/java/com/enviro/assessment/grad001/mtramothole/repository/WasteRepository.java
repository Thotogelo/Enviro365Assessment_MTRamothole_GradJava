package com.enviro.assessment.grad001.mtramothole.repository;

import com.enviro.assessment.grad001.mtramothole.model.Waste;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WasteRepository extends ListCrudRepository<Waste, Long> {

    Iterable<Waste> findWasteByWastecategory(String wastecategory);
}