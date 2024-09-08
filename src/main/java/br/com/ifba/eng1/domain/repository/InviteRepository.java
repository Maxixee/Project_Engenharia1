/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.eng1.domain.repository;

import br.com.ifba.eng1.domain.entities.Invite;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author lara
 */
public interface InviteRepository  extends JpaRepository<Invite, Long>{
    List<Invite> findByGuestId(Long guestId);
}
