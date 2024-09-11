/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.eng1.domain.dto.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import br.com.ifba.eng1.domain.dto.InviteViewDTO;
import br.com.ifba.eng1.domain.entities.Invite;
import org.modelmapper.ModelMapper;

/**
 *
 * @author lara
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InviteMapper {

    public static InviteViewDTO toDTO(Invite invite) {
        ModelMapper modelMapper = new ModelMapper();
        InviteViewDTO inviteDTO = modelMapper.map(invite, InviteViewDTO.class);

            
        inviteDTO.setGuestName(invite.getGuest().getName());
        inviteDTO.setSenderName(invite.getSender().getName());
        inviteDTO.setProjectName(invite.getProject().getName());
        inviteDTO.setGuestId(invite.getGuest().getId());
        inviteDTO.setSenderId(invite.getSender().getId());
        inviteDTO.setProjectId(invite.getProject().getId());

        return inviteDTO;
    }
}
