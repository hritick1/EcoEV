package com.system.EcoEV.lists;

import lombok.Data;

@Data
public class AllLists {
    private String name;

    private int totalIncome;

    private int totalDue;
    private int totalNotPaid;

    private int totalServiceCost;

    private String date;
}
