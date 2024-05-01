package com.system.EcoEV.lists;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MaintenanceList {
    String partsname;
    int cost;
    String date;
}
