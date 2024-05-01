package com.system.EcoEV.controller;

import com.system.EcoEV.dto.EvAllInOneDto;
import com.system.EcoEV.dto.EvDailyFinancesDto;
import com.system.EcoEV.dto.EvMaintenanceDto;
import com.system.EcoEV.entities.EvAllInOne;
import com.system.EcoEV.entities.EvMaintenance;
import com.system.EcoEV.lists.MaintenanceList;
import com.system.EcoEV.repo.EvAllInOneRepo;
import com.system.EcoEV.repo.EvDailyRepo;
import com.system.EcoEV.services.EvService;
import com.system.EcoEV.utils.CommonUtils;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class EvController {
    @Autowired
    private EvService evService;
    @Autowired
    private EvDailyRepo evDailyRepo;
    @Autowired
    private EvAllInOneRepo evAllInOneRepo;

    @GetMapping("/")
    private int getDate() {
        return CommonUtils.calculateTotalDate("2024-01-00");
    }

    @PostMapping("/addDaily/{name}")
    private String addDailyFinances(@RequestBody EvDailyFinancesDto dto, @PathVariable String name) {
        return evService.addEveryDayCollection(dto, name);
    }

    @GetMapping("/get/{name}")
    private Optional<EvAllInOne> getDailyFinances(@PathVariable String name) {
        return evAllInOneRepo.findById(name);
    }
    @GetMapping("/getDue/{name}")
    private String getDue(@PathVariable String name) {
        log.info("{}",name);
        return evService.getDueDates(name)+evService.findAllDue(name);
    }
    @GetMapping("/getAll/{name}")
    private String getTotalByName(@PathVariable String name){
        return evService.getAllByName(name);
    }
    @GetMapping("/getMaintenance/{name}")
    private List<MaintenanceList> getAllMaintenance(@PathVariable String name){
    return evService.getAllMaintenance(name);
    }
    @GetMapping("/addDaily/{name}/{dailyPay}/{costOfService}/{partsAdded}")
    private String addDailyFinanceMaintenancePost(@PathVariable String name,@PathVariable int dailyPay,@PathVariable int costOfService,@PathVariable String partsAdded){
   EvDailyFinancesDto evDailyFinancesDto=new EvDailyFinancesDto();
   evDailyFinancesDto.setName(name);
   evDailyFinancesDto.setDailyPay(dailyPay);
   evDailyFinancesDto.setMaintenance(costOfService);
   evDailyFinancesDto.setPartsAdded(partsAdded);
   return evService.addEveryDayCollection(evDailyFinancesDto,name);
    }
    @GetMapping("/addDaily/{name}/{dailyPay}")
    private String addDailyFinancePost(@PathVariable String name,@PathVariable int dailyPay){
        EvDailyFinancesDto evDailyFinancesDto=new EvDailyFinancesDto();
        evDailyFinancesDto.setName(name);
        evDailyFinancesDto.setDailyPay(dailyPay);
        return evService.addEveryDayCollection(evDailyFinancesDto,name);
    }
}
