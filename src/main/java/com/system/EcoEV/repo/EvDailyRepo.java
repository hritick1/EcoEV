package com.system.EcoEV.repo;

import com.system.EcoEV.entities.EvDailyFinances;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EvDailyRepo extends MongoRepository<EvDailyFinances,String> {
    List<EvDailyFinances> findByName(String name);
}
