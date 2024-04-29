package com.system.EcoEV.repo;

import com.system.EcoEV.entities.EvAllInOne;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EvAllInOneRepo extends MongoRepository<EvAllInOne,String> {

}
