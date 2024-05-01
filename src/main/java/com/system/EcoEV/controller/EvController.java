package com.system.EcoEV.controller;

import com.system.EcoEV.dto.EvDailyFinancesDto;
import com.system.EcoEV.lists.DailyFinancesList;
import com.system.EcoEV.lists.MaintenanceList;
import com.system.EcoEV.services.EvService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class EvController {
    @Autowired
    private EvService evService;

    @PostMapping("/addDaily/{name}")
    private String addDailyFinances(@RequestBody EvDailyFinancesDto dto, @PathVariable String name) {
        return evService.addEveryDayCollection(dto, name);
    }

    @GetMapping("/get/{name}")
    private List<DailyFinancesList> getDailyFinances(@PathVariable String name) {
        return evService.getDailyFinancesByName(name);
    }

    @GetMapping("/getDue/{name}")
    private String getDue(@PathVariable String name) {
        log.info("{}", name);
        return evService.getDueDates(name) + " : " + evService.findAllDue(name);
    }

    @GetMapping("/getTotal/{name}")
    private String getTotalByName(@PathVariable String name) {
        return evService.getAllByName(name);
    }

    @GetMapping("/getMaintenance/{name}")
    private List<MaintenanceList> getAllMaintenance(@PathVariable String name) {
        return evService.getAllMaintenance(name);
    }

    @GetMapping("/addDaily/{name}/{dailyPay}/{costOfService}/{partsAdded}")
    private String addDailyFinanceMaintenancePost(@PathVariable String name, @PathVariable int dailyPay, @PathVariable int costOfService, @PathVariable String partsAdded) {
        EvDailyFinancesDto evDailyFinancesDto = new EvDailyFinancesDto();
        evDailyFinancesDto.setName(name);
        evDailyFinancesDto.setDailyPay(dailyPay);
        evDailyFinancesDto.setMaintenance(costOfService);
        evDailyFinancesDto.setPartsAdded(partsAdded);
        return evService.addEveryDayCollection(evDailyFinancesDto, name);
    }

    @GetMapping("/addDaily/{name}/{dailyPay}")
    private String addDailyFinancePost(@PathVariable String name, @PathVariable int dailyPay) {
        EvDailyFinancesDto evDailyFinancesDto = new EvDailyFinancesDto();
        evDailyFinancesDto.setName(name);
        evDailyFinancesDto.setDailyPay(dailyPay);
        return evService.addEveryDayCollection(evDailyFinancesDto, name);
    }

    @GetMapping("/getMonthly/{monthName}/{name}")
    private String getMonthlyDetails(@PathVariable String monthName, @PathVariable String name) {
        return evService.getMontlyDetails(monthName, name);
    }
}
