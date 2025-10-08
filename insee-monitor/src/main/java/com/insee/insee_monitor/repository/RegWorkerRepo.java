package com.insee.insee_monitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.insee.insee_monitor.entity.RegWorker;

public interface RegWorkerRepo extends JpaRepository<RegWorker, Long> {
    Optional<RegWorker> findByEmail(String email);
    
}