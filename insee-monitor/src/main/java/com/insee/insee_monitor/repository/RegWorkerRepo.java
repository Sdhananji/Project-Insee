package com.insee.insee_monitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RegWorkerRepo extends JpaRepository<RegWorker, Long> {
    Optional<RegWorker> findByEmail(String email);
    
}