package com.insee.insee_monitor.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.insee.insee_monitor.repository.RegWorkerRepo;
import com.insee.insee_monitor.config.JwtService;
import com.insee.insee_monitor.dto.LoginRequestDTO;
import com.insee.insee_monitor.dto.LoginResponseDTO;
import com.insee.insee_monitor.entity.RegWorker;


@Service
@RequiredArgsConstructor

public class AuthServiceImpl implements AuthService{

    private final RegWorkerRepo workerRepo;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public LoginResponseDTO login (LoginRequestDTO request){
        RegWorker worker = workerRepo.findByEmail(request.getEmail())
            .orElseThrow(()-> new RuntimeException ("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), worker.getPassword())){
            throw new RuntimeException("Invalid email or password");
        }

        String token =jwtService.generateToken(worker.getEmail(),worker.getRole());

        return new LoginResponseDTO(token, worker.getRole());
            }
    
}
