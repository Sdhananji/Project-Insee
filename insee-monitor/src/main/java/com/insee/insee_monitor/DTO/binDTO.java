package com.insee.insee_monitor.dto;

import lombok.Data;

@Data

public class BinDTO {
    private String binNumber;
    private String binLocation;
    private double maxWeight;
    private double currentWeight;
    private String status;


    
}
