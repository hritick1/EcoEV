package com.system.EcoEV.repo;

import com.system.EcoEV.entities.EvMaintenance;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EvMaintenanceRepo extends MongoRepository<EvMaintenance,String> {
    List<EvMaintenance> findByName(String name);
}
