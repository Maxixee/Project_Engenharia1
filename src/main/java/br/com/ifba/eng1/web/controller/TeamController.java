/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.eng1.web.controller;

import br.com.ifba.eng1.domain.dto.TeamCreateDTO;
import br.com.ifba.eng1.domain.dto.TeamResponseDTO;
import br.com.ifba.eng1.domain.entities.Team;
import br.com.ifba.eng1.domain.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lara
 */

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/teams")
public class TeamController {
    
    @Autowired
    private TeamService service;
    
    @PostMapping("/save")
    public ResponseEntity<TeamResponseDTO> save(@RequestBody TeamCreateDTO dto){
        return ResponseEntity.ok(this.service.saveTeam(dto));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TeamResponseDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(this.service.getTeamById(id));
    }
    
     @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        this.service.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping
    public Page<Team>  findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
             @RequestParam(value = "direction", defaultValue = "asc") String direction) {
        
         var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "name"));
        
        return service.geAllTeams(pageable);
    }
}