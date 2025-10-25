package com.insee.insee_monitor.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BinDTO {
    private String binNumber;
    private String binLocation;
    private double maxCapacity;
    private double currentWeight;
    private String status;


    public double getMaxCapacity(){
        return maxCapacity;
    }
    public void setMaxCapacity (double maxCapacity){
        this.maxCapacity=maxCapacity;
    }


    
}
