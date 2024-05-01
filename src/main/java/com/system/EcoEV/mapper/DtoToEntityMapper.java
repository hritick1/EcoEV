package com.system.EcoEV.mapper;

import com.system.EcoEV.dto.EvAllInOneDto;
import com.system.EcoEV.dto.EvDailyFinancesDto;
import com.system.EcoEV.dto.EvMaintenanceDto;
import com.system.EcoEV.entities.EvAllInOne;
import com.system.EcoEV.entities.EvDailyFinances;
import com.system.EcoEV.entities.EvMaintenance;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "Spring")
public interface DtoToEntityMapper {
    DtoToEntityMapper instance= Mappers.getMapper(DtoToEntityMapper.class);
    EvAllInOne toEntity1(EvAllInOneDto dto);
    EvDailyFinances toEntity2(EvDailyFinancesDto dto);
//    EvMaintenance toEntity2(EvMaintenanceDto dto);
//    EvAllInOneDto toDto(EvAllInOne evAllInOne);
}
