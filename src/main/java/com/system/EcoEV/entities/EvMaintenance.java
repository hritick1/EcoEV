package com.system.EcoEV.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection = "ServiceCost")
public class EvMaintenance implements Serializable {
    @Id
    private String id;
    private String name;
    private String partName;
    private int costOfService;
    private String date;

}
