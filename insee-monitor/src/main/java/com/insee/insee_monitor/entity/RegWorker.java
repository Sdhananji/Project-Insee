package com.insee.insee_monitor.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="workers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class RegWorker {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String fname;
    private String lname;

    @Column(unique=true,nullable=false)
    private String email;

    private String password;

    @Builder.Default
    private boolean active = true;
    
}
