package com.insee.insee_monitor.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.insee.insee_monitor.entity.Bin;
import java.util.Optional;

public interface BinRepo extends JpaRepository<Bin, Long >{
    

    Optional<Bin>finfByEmail(String binNumber);
}
