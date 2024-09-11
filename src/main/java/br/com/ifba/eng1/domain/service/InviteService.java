/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.eng1.domain.service;

import java.util.List;
import br.com.ifba.eng1.domain.dto.InviteCreateDTO;
import br.com.ifba.eng1.domain.dto.InviteViewDTO;
import br.com.ifba.eng1.domain.dto.mapper.InviteMapper;
import br.com.ifba.eng1.domain.entities.Invite;
import br.com.ifba.eng1.domain.entities.Project;
import br.com.ifba.eng1.domain.entities.Users;
import br.com.ifba.eng1.domain.exception.EntityNotFoundException;
import br.com.ifba.eng1.domain.repository.InviteRepository;
import br.com.ifba.eng1.domain.repository.ProjectRepository;
import br.com.ifba.eng1.domain.repository.UsersRepository;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


/**
 *
 * @author lara
 */
@Service
@RequiredArgsConstructor
public class InviteService {

    private final InviteRepository inviteRepo;
    private final UsersRepository usersRepo;
    private final ProjectRepository projectRepo;
    
    public InviteViewDTO inviteUser(InviteCreateDTO data) {
        Users sender = usersRepo.findById(data.getSenderId()).orElseThrow(()
                -> new EntityNotFoundException("Sender not found"));

        Users guest = usersRepo.findById(data.getGuestId()).orElseThrow(()
                -> new EntityNotFoundException("Guest not found"));

        Project project = projectRepo.findById(data.getProjectId()).orElseThrow(()
                -> new EntityNotFoundException("Sender not found"));

        Invite newInvite = new Invite();

        newInvite.setGuest(guest);
        newInvite.setSender(sender);
        newInvite.setProject(project);
        newInvite.setStatus(Invite.InviteStatus.PENDING);

        try {
            inviteRepo.save(newInvite);
            return InviteMapper.toDTO(newInvite);
        } catch (Exception e) {
            throw e;
        }
    }

    public void acceptInvite(Long inviteId) {
        String email = getEmailByContext();
        Users authUser = usersRepo.findByEmail(email).orElseThrow(()
                -> new EntityNotFoundException("User not found"));

        Long guestId = authUser.getId();

        Invite invite = inviteRepo.findById(inviteId).orElseThrow(()
                -> new EntityNotFoundException("invite not found"));

        if (!invite.getGuest().getId().equals(guestId)) {
            throw new RuntimeException("This invite does not belong to authenticated user");
        }

        invite.setStatus(Invite.InviteStatus.ACCEPTED);
        invite.getProject().getMembers().add(invite.getGuest());

        try {
            inviteRepo.save(invite);
        } catch (Exception e) {
            throw e;
        }
    }

    public void refuseInvite(Long inviteId) {
        String email = getEmailByContext();
        Users authUser = usersRepo.findByEmail(email).orElseThrow(()
                -> new EntityNotFoundException("User not found"));

        Long guestId = authUser.getId();

        Invite invite = inviteRepo.findById(inviteId).orElseThrow(()
                -> new EntityNotFoundException("invite not found"));

        if (!invite.getGuest().getId().equals(guestId)) {
            throw new RuntimeException("This invitation does not belong to authenticated user");
        }

        invite.setStatus(Invite.InviteStatus.REFUSED);

        try {
            inviteRepo.save(invite);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<InviteViewDTO> findAllById() {
        String email = getEmailByContext();
        Users authUser = usersRepo.findByEmail(email).orElseThrow(()
                -> new EntityNotFoundException("User not found"));

        Long guestId = authUser.getId();

        try {
            List<Invite> invites = inviteRepo.findByGuestId(guestId);
            return invites.stream()
                .map(InviteMapper::toDTO) // Utiliza o método toDTO do InviteMapper
                .collect(Collectors.toList());
        } catch (Exception e) {
            throw e;
        }
    }

    public String getEmailByContext(){
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
