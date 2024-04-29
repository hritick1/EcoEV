package com.system.EcoEV.services.impl;

import com.system.EcoEV.dto.EvDailyFinancesDto;
import com.system.EcoEV.dto.EvMaintenanceDto;
import com.system.EcoEV.entities.EvAllInOne;
import com.system.EcoEV.entities.EvDailyFinances;
import com.system.EcoEV.mapper.DtoToEntityMapper;
import com.system.EcoEV.repo.EvAllInOneRepo;
import com.system.EcoEV.repo.EvDailyRepo;
import com.system.EcoEV.repo.EvMaintenanceRepo;
import com.system.EcoEV.services.EvService;
import com.system.EcoEV.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EvServiceImpl implements EvService {
    @Autowired
    private EvDailyRepo evDailyRepo;
    @Autowired
    private EvAllInOneRepo evAllInOneRepo;
    @Autowired
    private EvMaintenanceRepo evMaintenanceRepo;
    @Override
    public String addEveryDayCollection(EvDailyFinancesDto evDailyFinancesDto,String name) {
        evDailyFinancesDto.setDate(CommonUtils.getCurrentDate(new Date()));
        evDailyFinancesDto.setName(name);
        EvDailyFinances evDailyFinances= DtoToEntityMapper.instance.toEntity2(evDailyFinancesDto);
        evDailyRepo.save(evDailyFinances);
        String id=evDailyFinances.getId();
        EvAllInOne evAllInOne=new EvAllInOne();
        evAllInOne.setId(id);
        evAllInOne.setName(evDailyFinances.getName());
        evAllInOneRepo.save(evAllInOne);
        return evDailyFinancesDto.getName()+" paid "+evDailyFinancesDto.getDailyPay();
    }
}
