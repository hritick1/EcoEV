package com.system.EcoEV.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;


@Data
@Document(collection = "EvTotal")
public class EvAllInOne implements Serializable {
    @Id
    private String name;

    private int totalIncome;

    private int totalDue;

    private int totalServiceCost;

    private int totalNotPaid;

    private String date;
}
