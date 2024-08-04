/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.eng1.domain.dto.mapper;

import br.com.ifba.eng1.domain.dto.TeamCreateDTO;
import br.com.ifba.eng1.domain.dto.TeamResponseDTO;
import br.com.ifba.eng1.domain.entities.Team;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

/**
 *
 * @author lara
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TeamMapper {
    
     public static Team toTeam(TeamCreateDTO dto) {
        return new ModelMapper().map(dto, Team.class);
    }
    
     public static TeamResponseDTO toDTO(Team team) {
        return new ModelMapper().map(team, TeamResponseDTO.class);
    }
}
