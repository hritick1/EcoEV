package com.system.EcoEV.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection = "EveryDayCollection")
public class EvDailyFinances implements Serializable {

    @Id
    private String id;
    private String name;
    private int dailyPay;
    private int notPaid;

    private int due;
    private String date;

}
