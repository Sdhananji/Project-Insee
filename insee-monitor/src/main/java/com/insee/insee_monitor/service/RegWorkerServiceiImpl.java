package com.insee.insee_monitor.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

import com.insee.insee_monitor.repository.RegWorkerRepo;

import lombok.RequiredArgsConstructor;
import com.insee.insee_monitor.dto.RegWorkerDTO;
import com.insee.insee_monitor.entity.RegWorker;    
import com.insee.insee_monitor.util.PasswordGenerator;
import org.springframework.mail.SimpleMailMessage;

@Service
@RequiredArgsConstructor

public class RegWorkerServiceiImpl implements RegWorkerService {
    private final RegWorkerRepo regWorkerRepo;
    private final JavaMailSender mailSender;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public String registerWorker(RegWorkerDTO regWorkerDTO){
        //1. generate paword
        String rawPassword = PasswordGenerator.generatePassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);

        //2. Create worker object
        RegWorker regworker = RegWorker.builder()
        .fname(regWorkerDTO.getFname())
        .lname(regWorkerDTO.getLname())
        .email(regWorkerDTO.getEmail())
        .password(encodedPassword)
        .role(regWorkerDTO.getRole() !=null ? regWorkerDTO.getRole() : "WORKER")    
        .build();

        //3. Save in DB
        regWorkerRepo.save(regworker);

        //4. Send email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(regWorkerDTO.getEmail());
        message.setSubject("Your INSEE Worker Account");
        message.setText("Hi " + regWorkerDTO.getFname() + ",\n\n" +
                        "Your account has been created successfully. Here are your login details:\n" +
                        "Email: " + regWorkerDTO.getEmail() + "\n" +
                        "Password: " + rawPassword + "\n\n" +
                        "Please change your password after logging in for the first time.\n\n" +
                        "Best regards,\n" +
                        "INSEE Team");

        mailSender.send(message);

        return "Worker registered successfully. An email has been sent";
    }


}
