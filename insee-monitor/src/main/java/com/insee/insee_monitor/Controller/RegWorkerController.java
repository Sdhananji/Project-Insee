package com.insee.insee_monitor.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insee.insee_monitor.dto.RegWorkerDTO;
import com.insee.insee_monitor.service.RegWorkerService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/workers")
@RequiredArgsConstructor


public class RegWorkerController {


    private final RegWorkerService regWorkerService;
    @PostMapping("/reg")
    public ResponseEntity<String> registerWorker (@RequestBody RegWorkerDTO regWorkerDTO) {
        String response = regWorkerService.registerWorker(regWorkerDTO);
        return ResponseEntity.ok(response);
    }

}
