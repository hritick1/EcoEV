package com.system.EcoEV.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EvDue {
    private int due;
    private String date;
}
