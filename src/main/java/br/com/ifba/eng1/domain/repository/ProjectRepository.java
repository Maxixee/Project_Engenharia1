/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.eng1.domain.repository;

import br.com.ifba.eng1.domain.entities.Project;
import br.com.ifba.eng1.domain.repository.projection.ProjectProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 *
 * @author lara
 */
public interface ProjectRepository extends JpaRepository<Project, Long>{

    Optional<Project> findByName(String name);

    @Query("select c from Project c")
    Page<ProjectProjection> findAllPageable(Pageable pageable);
    
}
