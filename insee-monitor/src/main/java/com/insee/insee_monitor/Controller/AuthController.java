package com.insee.insee_monitor.Controller;

import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import com.insee.insee_monitor.service.AuthService;
import com.insee.insee_monitor.dto.LoginRequestDTO;
import com.insee.insee_monitor.dto.LoginResponseDTO;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin(origins = "http://localhost:3000")
@Controller
@RequestMapping("/api/auth")
@RequiredArgsConstructor


public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request){
        LoginResponseDTO response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    
}
