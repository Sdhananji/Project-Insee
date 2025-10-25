package com.insee.insee_monitor.entity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import lombok.Builder;
import jakarta.persistence.Id;


@Entity

@Table(name="bins")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class Bin {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id; // like BIN001, BIN002

    @Column(unique=true, nullable=false)
    private String binNumber;

    private String binLocation;

    private double MaxCapacity; //in kg or tons
    private double currentWeight; //realtime updated weights of each bin

    private String status; //EMPTY,NORMAL,FULL
    
}
