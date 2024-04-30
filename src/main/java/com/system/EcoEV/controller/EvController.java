package com.system.EcoEV.controller;

import com.system.EcoEV.dto.EvDailyFinancesDto;
import com.system.EcoEV.entities.EvAllInOne;
import com.system.EcoEV.repo.EvAllInOneRepo;
import com.system.EcoEV.repo.EvDailyRepo;
import com.system.EcoEV.services.EvService;
import com.system.EcoEV.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private int getDue(@PathVariable String name) {
        log.info("{}",name);
        return evService.findAllDue(name);
    }
}
