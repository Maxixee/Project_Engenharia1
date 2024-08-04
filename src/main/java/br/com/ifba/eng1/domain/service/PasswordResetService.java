/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.eng1.domain.service;

import br.com.ifba.eng1.domain.entities.Users;
import br.com.ifba.eng1.domain.exception.EntityNotFoundException;
import br.com.ifba.eng1.domain.repository.UsersRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author lara
 */
@Service
@RequiredArgsConstructor
public class PasswordResetService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UsersRepository userRepo;

    private final PasswordEncoder passwordEncoder;

    public void sendPasswordResetEmail(String email) {
        System.out.println(email);
        
        var user = this.userRepo.findByEmail(email);
        
        if(user.isEmpty()){
            throw new EntityNotFoundException("Email not in DB");
        }

        String token = UUID.randomUUID().toString();
        user.get().setPasswordResetToken(token);
        this.userRepo.save(user.get());

        String url = "http://localhost:8080/resetPassword?token=" + token;

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("SCRUM MANAGER PASSWORD RESET LINK");
            message.setText("Click the link to reset your password: " + url);

            mailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException("Error Sending Email");
        }
    }

    public void resetPassword(String token, String newPassword) {
 System.out.println("Searching for token: " + token);
        var user = this.userRepo.findByPasswordResetToken(token);
        
        if(user.isEmpty()){
            throw new EntityNotFoundException("token not valid");
        }
            

        user.get().setPassword(this.passwordEncoder.encode(newPassword));
        user.get().setPasswordResetToken(null);
        this.userRepo.save(user.get());
    }
}

