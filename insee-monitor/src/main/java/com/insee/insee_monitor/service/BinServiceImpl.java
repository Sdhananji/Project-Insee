package com.insee.insee_monitor.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.insee.insee_monitor.repository.BinRepo;
import com.insee.insee_monitor.dto.BinDTO;
import com.insee.insee_monitor.entity.Bin;
import java.util.List;
import java.util.stream.Collectors;
import java.lang.RuntimeException;




@Service
@RequiredArgsConstructor

public class BinServiceImpl implements BinService { 
    private final BinRepo binRepo;

    @Override
    public String registerBins(BinDTO binDto){
        if(binRepo.findByBinNumber(binDto.getBinNumber()).isPresent()){
            throw new RuntimeException ("Bin already Exists!");

        }

        Bin bin =Bin.builder()
            .binNumber(binDto.getBinNumber())
            .binLocation(binDto.getBinLocation())
            .maxCapacity(binDto.getMaxCapacity())
            .currentWeight(binDto.getCurrentWeight())
            .status("EMPTY")
            .build();

            binRepo.save(bin);
            return "Bin Registered Successfully!";


    }

    public BinDTO getBinByBinNumber(String binNumber){
        System.out.println("Looking for the bin: "+binNumber);
        return binRepo.findByBinNumber(binNumber)
            .map(bin -> {
                System.out.println("Found bin: "+bin.getBinNumber());
                BinDTO binDto = new BinDTO();
                binDto.setBinNumber(bin.getBinNumber());
                binDto.setBinLocation(bin.getBinLocation());
                binDto.setMaxCapacity(bin.getMaxCapacity());
                binDto.setCurrentWeight(bin.getCurrentWeight());
                binDto.setStatus(bin.getStatus());
                return binDto;
            })
            .orElse(null); // return null if bin not found
    }


    @Override
    public List<BinDTO> getAllBins(){
        return binRepo.findAll().stream()
                .map(bin ->{
                    BinDTO binDto=new BinDTO();
                    binDto.setBinNumber(bin.getBinNumber());
                    binDto.setBinLocation(bin.getBinLocation());
                    binDto.setMaxCapacity(bin.getMaxCapacity());
                    binDto.setCurrentWeight(bin.getCurrentWeight());
                    binDto.setStatus(bin.getStatus());
                    return binDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public String updateBinWeight(String binNumber, double weight){
        Bin bin = binRepo.findByBinNumber(binNumber)
            .orElseThrow(()->new RuntimeException("Bin not found!"));

            bin.setCurrentWeight(weight);

            //update status automatically
            if(weight==0) bin.setStatus("EMPTY");
            else if(weight>=bin.getMaxCapacity())bin.setStatus("FULL");
            else bin.setStatus("NORMAL");

            binRepo.save(bin);
            return "Bin weight updated successfully";

        }

    
    

    

    
}
