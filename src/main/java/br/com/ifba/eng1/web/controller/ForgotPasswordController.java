/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.eng1.web.controller;

import br.com.ifba.eng1.domain.dto.EmailDTO;
import br.com.ifba.eng1.domain.dto.NewPasswordDTO;
import br.com.ifba.eng1.domain.service.PasswordResetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author lara
 */
@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/resetPassword")
public class ForgotPasswordController {

    @Autowired
    private PasswordResetService service;

    @PostMapping
    public ResponseEntity<String> sendResetPasswordEmail(@RequestBody EmailDTO email) {
        System.out.println("Received email: " + email.getEmail());

        this.service.sendPasswordResetEmail(email.getEmail());
        return ResponseEntity.ok("Password request link sent, please check your email.");

    }

    @PatchMapping
    public ResponseEntity<String> sendResetPasswordEmail(@RequestParam String token, @RequestBody NewPasswordDTO newPassword) {
        System.out.println("Received token: " + token);
        System.out.println("Received new password: " + newPassword.getNewPasword());

        this.service.resetPassword(token, newPassword.getNewPasword());
        return ResponseEntity.ok("Password updated");
    }

}
