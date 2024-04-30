package com.system.EcoEV.services.impl;

import com.system.EcoEV.dto.EvDailyFinancesDto;
import com.system.EcoEV.entities.EvAllInOne;
import com.system.EcoEV.exception.CollectionNotFoundException;
import com.system.EcoEV.repo.EvAllInOneRepo;
import com.system.EcoEV.repo.EvDailyRepo;
import com.system.EcoEV.repo.EvMaintenanceRepo;
import com.system.EcoEV.services.EvService;
import com.system.EcoEV.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class EvServiceImpl implements EvService {
    @Autowired
    private EvDailyRepo evDailyRepo;
    @Autowired
    private EvAllInOneRepo evAllInOneRepo;
    @Autowired
    private EvMaintenanceRepo evMaintenanceRepo;

    @Override
    public String addEveryDayCollection(EvDailyFinancesDto evDailyFinancesDto, String name) {


        return null;
    }

    @Override
    public int findAllDue(String name){
        log.info(name);
        EvAllInOne evAllInOne = evAllInOneRepo.findById(name).orElseThrow(() -> new CollectionNotFoundException("Collection not found with name: " + name));
        log.info(name);
        String date = evAllInOne.getDate();

        int prevDue = evAllInOne.getTotalDue();
        int totalDays = CommonUtils.calculateTotalDate(date);

        log.info("{}", totalDays);

        evAllInOne.setTotalDue(totalDays * 200 + prevDue);
        evAllInOne.setDate(CommonUtils.getCurrentDate(new Date()));
        evAllInOneRepo.save(evAllInOne);

        return evAllInOne.getTotalDue();
    }

    @Override
    public String getDueDates(String name) {
        String currentDate=CommonUtils.getCurrentDate(new Date());
        EvAllInOne evAllInOne=evAllInOneRepo.findById(name).orElseThrow(()->new CollectionNotFoundException("Collection not found with name: "+name));
        String givenDate=evAllInOne.getDate();
        return "Due From "+(Integer.parseInt(givenDate.substring(8,10))+1)+"-"+givenDate.substring(5,7)+" to "+currentDate.substring(8,10)+"-"+currentDate.substring(5,7)+" Amount:";
    }
}

//yyyy-mm-dd
//0123456789