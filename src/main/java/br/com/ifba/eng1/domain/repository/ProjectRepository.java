/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.eng1.domain.repository;

import br.com.ifba.eng1.domain.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author lara
 */
public interface ProjectRepository extends JpaRepository<Project, Long>{
    
}
