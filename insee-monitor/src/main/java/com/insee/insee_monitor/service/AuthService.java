package com.insee.insee_monitor.service;
import com.insee.insee_monitor.dto.LoginRequestDTO;
import com.insee.insee_monitor.dto.LoginResponseDTO;

public interface AuthService {
    LoginResponseDTO login(LoginRequestDTO request);

    
}
