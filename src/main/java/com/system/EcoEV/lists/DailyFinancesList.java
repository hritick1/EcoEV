package com.system.EcoEV.lists;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DailyFinancesList {
    private String name;
    private int dailyPay;
    private String date;
}
