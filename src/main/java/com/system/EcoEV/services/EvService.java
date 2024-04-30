package com.system.EcoEV.services;

import com.system.EcoEV.dto.EvDailyFinancesDto;

public interface EvService {
   String addEveryDayCollection(EvDailyFinancesDto evDailyFinancesDto,String name);
   int findAllDue(String name);
   String getDueDates(String name);
}
