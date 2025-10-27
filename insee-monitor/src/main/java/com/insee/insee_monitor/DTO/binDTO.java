package com.insee.insee_monitor.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BinDTO {
    @JsonProperty("binNumber")
    private String binNumber;

    @JsonProperty("binLocation")
    private String binLocation;

    @JsonProperty("maxCapacity")
    private double maxCapacity;

    @JsonProperty("currentWeight")
    private double currentWeight;

    @JsonProperty("status")
    private String status;




    
}
