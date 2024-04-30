package com.system.EcoEV.services;

import com.system.EcoEV.dto.EvDailyFinancesDto;
import com.system.EcoEV.entities.EvDue;

import java.util.List;

public interface EvService {
   String addEveryDayCollection(EvDailyFinancesDto evDailyFinancesDto,String name);
   int findAllDue(String name);
}
