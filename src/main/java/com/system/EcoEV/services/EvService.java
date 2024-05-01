package com.system.EcoEV.services;

import com.system.EcoEV.dto.EvDailyFinancesDto;
import com.system.EcoEV.entities.EvAllInOne;
import com.system.EcoEV.lists.DailyFinancesList;
import com.system.EcoEV.lists.MaintenanceList;

import java.util.List;

public interface EvService {
    String addEveryDayCollection(EvDailyFinancesDto evDailyFinancesDto, String name);

    int findAllDue(String name);

    String getDueDates(String name);

    String getAllByName(String name);

    List<MaintenanceList> getAllMaintenance(String name);

    void setMonthRepo(EvAllInOne evAllInOne);

    String getMontlyDetails(String monthName, String name);

    List<DailyFinancesList> getDailyFinancesByName(String name);
}
