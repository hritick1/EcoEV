package com.system.EcoEV.controller;

import com.sun.tools.javac.Main;
import com.system.EcoEV.dto.EvDailyFinancesDto;
import com.system.EcoEV.dto.TotalDataDto;
import com.system.EcoEV.entities.EvAllInOne;
import com.system.EcoEV.entities.EvMaintenance;
import com.system.EcoEV.entities.EvMonth;
import com.system.EcoEV.lists.AllLists;
import com.system.EcoEV.lists.DailyFinancesList;
import com.system.EcoEV.lists.MaintenanceList;
import com.system.EcoEV.repo.EvAllInOneRepo;
import com.system.EcoEV.repo.EvMaintenanceRepo;
import com.system.EcoEV.services.EvService;
import com.system.EcoEV.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/")
public class EvController {
    @Autowired
    private EvService evService;
    @Autowired
    private EvAllInOneRepo evAllInOneRepo;

    @PostMapping("addDaily/{name}")
    private String addDailyFinances(@RequestBody EvDailyFinancesDto dto, @PathVariable String name) {
        return evService.addEveryDayCollection(dto, name);
    }

    @GetMapping("get/{name}")
    private List<DailyFinancesList> getDailyFinances(@PathVariable String name) {
        return evService.getDailyFinancesByName(name);
    }

    @GetMapping("getDue/{name}")
    private String getDue(@PathVariable String name) {
        log.info("{}", name);
        return evService.getDueDates(name) + " : " + evService.findAllDue(name);
    }

    @GetMapping("getTotal/{name}")
    private AllLists getTotalByName(@PathVariable String name) {
        return evService.getAllByName(name);
    }

    @GetMapping("getMaintenance/{name}")
    private List<MaintenanceList> getAllMaintenance(@PathVariable String name) {
        return evService.getAllMaintenance(name);
    }



    @GetMapping("getMonthly/{monthName}/{name}")
    private EvMonth getMonthlyDetails(@PathVariable String monthName, @PathVariable String name) {
        return evService.getMontlyDetails(monthName, name);
    }
   @GetMapping("getTotalData/{monthName}")
    public TotalDataDto getNetResults(@PathVariable String monthName){
        return evService.getNetResults(monthName);
   }

//   @GetMapping("/month")
//    public void month(){
//        EvAllInOne evAllInOne=evAllInOneRepo.findById("Gathu").get();
//        evService.setMonthRepo(evAllInOne);
//   }

}
