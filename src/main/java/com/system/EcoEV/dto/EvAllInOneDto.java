package com.system.EcoEV.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
@Data
public class EvAllInOneDto {
    @Id
    private String name;

    private int totalIncome;

    private int totalDue;

    private int totalServiceCost;

    private String date;
}
