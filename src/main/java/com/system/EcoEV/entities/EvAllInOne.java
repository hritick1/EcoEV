package com.system.EcoEV.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;


@Data
@Document(collection = "EvTotal")
public class EvAllInOne implements Serializable {
    @Id
    private String name;

    private String id;

    private int totalIncome;

    private int totalDue;

    private int totalServiceCost;

    private String date;
}
