package com.system.EcoEV.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
@Data
public class EvDailyFinancesDto {
    @Id
    private String id;
    private String name;
    private int dailyPay;
    private int maintenance;
    private String partsAdded;
    private String date;
}
