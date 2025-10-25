package com.insee.insee_monitor.service;
import com.insee.insee_monitor.dto.BinDTO;
import java.util.List;




public interface BinService {

    String registerBins(BinDTO bindto);
    BinDTO getByBinNumber(String binNumber);
    List<BinDTO> getAllBins();
    String updateBinWeight(String binNumber, double weight);

    
}
