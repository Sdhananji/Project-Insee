package com.insee.insee_monitor.Controller;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import com.insee.insee_monitor.service.BinService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.insee.insee_monitor.dto.BinDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/bins")
@RequiredArgsConstructor

public class BinController {

    private final BinService binService;

    @PostMapping("/register")
    public ResponseEntity<String>registerBin(@RequestBody BinDTO binDto){
        return ResponseEntity.ok(binService.registerBins(binDto));
    }

    @GetMapping("/{binNumber}")
    public ResponseEntity<BinDTO> getBin(@PathVariable String binNumber){
        return ResponseEntity.ok(binService.getBinByBinNumber(binNumber));
    }

    @GetMapping
    public ResponseEntity<List<BinDTO>> getAllBins(){
        return ResponseEntity.ok(binService.getAllBins());
    }

    @PutMapping("/{binNumber}/weight")
    public ResponseEntity<String> updateWeight(
        @PathVariable String binNumber,
        @RequestParam double weight
    ){
        return ResponseEntity.ok(binService.updateBinWeight(binNumber,weight));
    }



    
}
