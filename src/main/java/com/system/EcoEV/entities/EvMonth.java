package com.system.EcoEV.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection = "MonthlyData")
public class EvMonth implements Serializable {
    @Id
    private String id;
    private String monthName;
    private String name;
    private int totalIncome;
    private int totalNotPaid;
    private int totalDue;
    private int totalServiceCost;
    private String date;
}
