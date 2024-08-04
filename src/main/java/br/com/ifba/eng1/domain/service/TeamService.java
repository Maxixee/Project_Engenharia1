/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.eng1.domain.service;

import br.com.ifba.eng1.domain.dto.TeamCreateDTO;
import br.com.ifba.eng1.domain.dto.TeamResponseDTO;
import br.com.ifba.eng1.domain.dto.mapper.TeamMapper;
import br.com.ifba.eng1.domain.entities.Team;
import br.com.ifba.eng1.domain.exception.EntityNotFoundException;
import br.com.ifba.eng1.domain.exception.InvalidRegistrationInformationException;
import br.com.ifba.eng1.domain.repository.TeamRepository;
import br.com.ifba.eng1.domain.repository.ProjectRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author lara
 */
@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;
    //@Autowired
    //private ProjectRepository projectRepository;
   
    public Page<Team> geAllTeams(Pageable pageable){
        Page<Team> teams = this.teamRepository.findAll(pageable);
        if(teams.isEmpty()){
            throw new RuntimeException("No teams found");
        }
        return teams;
    }
    
    public TeamResponseDTO getTeamById(Long id){
        var team = this.teamRepository.findById(id);
        
        if(team.isEmpty()){
            throw new EntityNotFoundException("No team found with this id");
        }
        
        return TeamMapper.toDTO(team.get());
    }
    
    public TeamResponseDTO saveTeam(TeamCreateDTO newTeam){
        if(newTeam.getName().isBlank() || newTeam.getName().isEmpty() || newTeam.getProject() == null){
            throw new InvalidRegistrationInformationException("Invalid team details. Team not created.");
        }
        
        //Team team = new Team();
        var response = this.teamRepository.save(TeamMapper.toTeam(newTeam));
        
        return TeamMapper.toDTO(response);
    }
    
    public void deleteTeam(Long id){
        var team = this.teamRepository.findById(id);
        
        if(team.isEmpty()){
            throw new EntityNotFoundException("Team with id: " +id.toString() +"not found");
        }
        
        this.teamRepository.delete(team.get());
    }
    
    
    
}
