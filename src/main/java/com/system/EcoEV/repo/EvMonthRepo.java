package com.system.EcoEV.repo;

import com.system.EcoEV.entities.EvMonth;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EvMonthRepo extends MongoRepository<EvMonth,String> {
    EvMonth findByMonthNameAndName(String monthName,String name);
}
