package com.enviro.assessment.grad001.mtramothole.repository;

import com.enviro.assessment.grad001.mtramothole.model.Waste;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WasteRepository extends CrudRepository<Waste, Long> {
}