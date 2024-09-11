/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.eng1.domain.dto;

import br.com.ifba.eng1.domain.entities.Invite.InviteStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author lara
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InviteViewDTO {

    private Long id;
    private Long guestId;
    private String guestName;
    private Long senderId;
    private String senderName;
    private Long projectId;
    private String projectName;
    private InviteStatus status;
}
