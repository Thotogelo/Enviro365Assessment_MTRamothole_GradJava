package com.enviro.assessment.grad001.mtramothole.repository;

import com.enviro.assessment.grad001.mtramothole.model.Waste;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WasteRepository extends CrudRepository<Waste, Long> {

    // Method to delete a waste object by its id. Returns the number of rows affected.
    int deleteWasteById(Long id);

    // Method to find a waste object by its id. Returns the found waste object.
    Waste findWasteById(Long id);

    // Method to delete waste objects by their category. Returns the number of rows affected.
    int deleteWasteByWastecategory(String wastecategory);

    // Method to find waste objects by their category. Returns a list of found waste objects.
    List<Waste> findWasteByWastecategory(String wastecategory);

    // Method to find all waste objects. Returns a list of all waste objects.
    @Override
    List<Waste> findAll();
}