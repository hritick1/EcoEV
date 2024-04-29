package com.system.EcoEV.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class EvMaintenanceDto {
    @Id
    private String id;
    private String name;
    private int costOfService;
    private String date;
}
