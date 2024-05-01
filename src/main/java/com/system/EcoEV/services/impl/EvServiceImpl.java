package com.system.EcoEV.services.impl;

import com.system.EcoEV.dto.EvDailyFinancesDto;
import com.system.EcoEV.dto.EvMaintenanceDto;
import com.system.EcoEV.entities.EvAllInOne;
import com.system.EcoEV.entities.EvDailyFinances;
import com.system.EcoEV.entities.EvMaintenance;
import com.system.EcoEV.entities.EvMonth;
import com.system.EcoEV.exception.CollectionNotFoundException;
import com.system.EcoEV.lists.MaintenanceList;
import com.system.EcoEV.repo.EvAllInOneRepo;
import com.system.EcoEV.repo.EvDailyRepo;
import com.system.EcoEV.repo.EvMaintenanceRepo;
import com.system.EcoEV.repo.EvMonthRepo;
import com.system.EcoEV.services.EvService;
import com.system.EcoEV.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EvServiceImpl implements EvService {
    @Autowired
    private EvDailyRepo evDailyRepo;
    @Autowired
    private EvAllInOneRepo evAllInOneRepo;
    @Autowired
    private EvMaintenanceRepo evMaintenanceRepo;

    @Autowired
    private EvMonthRepo evMonthRepo;

    @Override
    public String addEveryDayCollection(EvDailyFinancesDto evDailyFinancesDto, String name) {
//        //remove only for 1st use
//        EvAllInOne evAllInOne1=new EvAllInOne();
//        evAllInOne1.setName(name);
//        evAllInOne1.setDate(CommonUtils.getCurrentDate(new Date()));
//        evAllInOneRepo.save(evAllInOne1);

        EvAllInOne evAllInOne = evAllInOneRepo.findById(name).orElseThrow(() -> new CollectionNotFoundException("Collection not found with name: " + name));
        EvDailyFinances evDailyFinances = new EvDailyFinances();
        evDailyFinances.setName(name);
        evDailyFinances.setDate(CommonUtils.getCurrentDate(new Date()));
        //setting Due
        int dailyPay = evDailyFinancesDto.getDailyPay();
        int due = findAllDue(name);
        int paid=0;
        if (dailyPay > 200) {
            evDailyFinances.setDailyPay(200);
            paid=200;
            evAllInOne.setTotalDue(due - (dailyPay - 200));
        } else if (dailyPay < 200) {
            evAllInOne.setTotalDue(due + 200 - dailyPay);
            paid=dailyPay;
        } else{
            evDailyFinances.setDailyPay(200);
        paid=200;
        }
        //Setting maintenance
        EvMaintenance evMaintenance = new EvMaintenance();
        if (evDailyFinancesDto.getPartsAdded() != null) {
            evMaintenance.setCostOfService(evDailyFinancesDto.getMaintenance());
            evMaintenance.setDate(CommonUtils.getCurrentDate(new Date()));
            evMaintenance.setPartName(evDailyFinancesDto.getPartsAdded());
            evMaintenance.setName(name);
            evMaintenanceRepo.save(evMaintenance);
        }
        //Setting all in one
        evAllInOne.setDate(CommonUtils.getCurrentDate(new Date()));
        evAllInOne.setTotalIncome(evAllInOne.getTotalIncome() + dailyPay);
        evAllInOne.setTotalServiceCost(evAllInOne.getTotalServiceCost() + evDailyFinancesDto.getMaintenance());
        evAllInOne.setName(name);

        evDailyRepo.save(evDailyFinances);
        evAllInOneRepo.save(evAllInOne);

        if(CommonUtils.isLastDayOfMonth(evAllInOne.getDate())){
            setMonthRepo(evAllInOne);
        }

        return "Daily Paid: " + paid;
    }


    @Override
    public int findAllDue(String name) {
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
        String currentDate = CommonUtils.getCurrentDate(new Date());
        EvAllInOne evAllInOne = evAllInOneRepo.findById(name).orElseThrow(() -> new CollectionNotFoundException("Collection not found with name: " + name));
        String givenDate = evAllInOne.getDate();
        return "Due From " + (Integer.parseInt(givenDate.substring(8, 10)) + 1) + "-" + givenDate.substring(5, 7) + " to " + currentDate.substring(8, 10) + "-" + currentDate.substring(5, 7) + " Amount:";
    }

    @Override
    public String getAllByName(String name) {
        EvAllInOne evAllInOne=evAllInOneRepo.findById(name).orElseThrow(()->new CollectionNotFoundException("Collection not found with name: "+name));
        return "Name: "+evAllInOne.getName()+" Total-Profit: "+evAllInOne.getTotalIncome()+" Total-Due: "+evAllInOne.getTotalDue()+" Total-Maintenance: "+evAllInOne.getTotalServiceCost();
    }

    @Override
    public List<MaintenanceList> getAllMaintenance(String name) {
        List<EvMaintenance> evMaintenance=evMaintenanceRepo.findByName(name);
        List<MaintenanceList> list= evMaintenance.stream()
                .map((ev)->new MaintenanceList(ev.getPartName(),ev.getCostOfService(),ev.getDate()))
                .collect(Collectors.toList());
        return list;

    }
    public void setMonthRepo(EvAllInOne evAllInOne){
        String date=evAllInOne.getDate();
   int mm=Integer.parseInt(date.substring(5,7));
   String month=CommonUtils.getMonthName(mm);
        EvMonth evMonth=new EvMonth();
        evMonth.setMonthName(month+date.substring(8,10));
        evMonth.setDate(CommonUtils.getCurrentDate(new Date()));
        evMonth.setName(evAllInOne.getName());
        evMonth.setTotalIncome(evAllInOne.getTotalIncome());
        evMonth.setTotalDue(evAllInOne.getTotalDue());
        evMonth.setTotalServiceCost(evAllInOne.getTotalServiceCost());

        evMonthRepo.save(evMonth);

//        setting EvAllInOne to zero for next month calculation
        evAllInOne.setTotalIncome(0);
        evAllInOne.setTotalServiceCost(0);
        evAllInOneRepo.save(evAllInOne);
    }

    @Override
    public String getMontlyDetails(String monthName, String name) {
        EvMonth evMonth=evMonthRepo.findByMonthNameAndName(monthName,name);
        return "Month: "+evMonth.getMonthName()+"TotalDue: "+ evMonth.getTotalDue()+
                "TotalIncome: "+ evMonth.getTotalIncome()+"TotalMaintenance: "+evMonth.getTotalServiceCost();
    }
}
//yyyy-mm-dd
//0123456789
