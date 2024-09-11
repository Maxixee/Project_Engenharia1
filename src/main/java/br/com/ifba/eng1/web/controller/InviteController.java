/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.eng1.web.controller;

import br.com.ifba.eng1.domain.dto.InviteCreateDTO;
import br.com.ifba.eng1.domain.dto.InviteViewDTO;
import br.com.ifba.eng1.domain.service.InviteService;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;


/**
 *
 * @author lara
 */

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/invites")
public class InviteController {
    
    private final InviteService inviteService;
    
    @PostMapping("/invite")
    public ResponseEntity<InviteViewDTO> inviteGuest(@RequestBody InviteCreateDTO data){
        InviteViewDTO invite = inviteService.inviteUser(data);
        
        return ResponseEntity.ok(invite);
    }
    
    @PostMapping("/accept/{id}")
    public ResponseEntity<String> acceptInvite(@PathVariable Long id){
        try {
            inviteService.acceptInvite(id);
            return ResponseEntity.ok("Invitation accepted!");
        } catch (Exception e) {
            throw e;
        }
    }
    
    @PostMapping("/refuse/{id}")
    public ResponseEntity<String> refuseInvite(@PathVariable Long id){
        try {
            inviteService.refuseInvite(id);
            return ResponseEntity.ok("Invitation accepted!");
        } catch (Exception e) {
            throw e;
        }
    }
    
    @GetMapping("/my-invites")
    public ResponseEntity<List<InviteViewDTO>> findMyInvites(){
        List<InviteViewDTO> myInvites = this.inviteService.findAllById();
        try {
            return ResponseEntity.ok(myInvites);
        } catch (Exception e) {
            throw e;
        }
    }
}
