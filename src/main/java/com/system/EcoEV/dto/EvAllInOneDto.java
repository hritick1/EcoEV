package com.system.EcoEV.dto;

import com.system.EcoEV.entities.EvDue;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class EvAllInOneDto {
    @Id
    private String name;

    private int totalIncome;

    private int totalDue;

    private int totalServiceCost;

    private String date;
}
