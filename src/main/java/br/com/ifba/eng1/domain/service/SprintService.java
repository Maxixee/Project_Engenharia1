package br.com.ifba.eng1.domain.service;

import br.com.ifba.eng1.domain.dto.SprintCreateDTO;
import br.com.ifba.eng1.domain.dto.SprintViewDTO;
import br.com.ifba.eng1.domain.dto.mapper.SprintMapper;
import br.com.ifba.eng1.domain.entities.Project;
import br.com.ifba.eng1.domain.entities.Sprint;
import br.com.ifba.eng1.domain.exception.ResourceNotFoundException;
import br.com.ifba.eng1.domain.repository.ProjectRepository;
import br.com.ifba.eng1.domain.repository.SprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SprintService {

    @Autowired
    private SprintRepository sprintRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public SprintViewDTO save(SprintCreateDTO sprintDTO) {
        Project project = projectRepository.findById(sprintDTO.getIdProject())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        Sprint sprint = new Sprint();
        sprint.setIdProject(project);
        sprint.setName(sprintDTO.getName());
        sprint.setStartDate(sprintDTO.getStartDate());
        sprint.setEndDate(sprintDTO.getEndDate());
        sprint.setStatus(sprintDTO.isStatus());

        Sprint savedSprint = sprintRepository.save(sprint);
        return SprintMapper.toDTO(savedSprint);  // Aqui usamos o SprintMapper
    }

    @Transactional(readOnly = true)
    public SprintViewDTO findById(Long id) {
        Sprint sprint = sprintRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sprint not found"));
        return SprintMapper.toDTO(sprint);  // Aqui usamos o SprintMapper
    }

    @Transactional(readOnly = true)
    public List<SprintViewDTO> findAll() {
        return sprintRepository.findAll()
                .stream()
                .map(SprintMapper::toDTO)  // Aqui usamos o SprintMapper
                .collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public SprintViewDTO update(Long id, SprintCreateDTO sprintDTO) {
        Sprint sprint = sprintRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sprint not found"));

        Project project = projectRepository.findById(sprintDTO.getIdProject())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        sprint.setIdProject(project);
        sprint.setName(sprintDTO.getName());
        sprint.setStartDate(sprintDTO.getStartDate());
        sprint.setEndDate(sprintDTO.getEndDate());
        sprint.setStatus(sprintDTO.isStatus());

        Sprint updatedSprint = sprintRepository.save(sprint);
        return SprintMapper.toDTO(updatedSprint);  // Aqui usamos o SprintMapper
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        Sprint sprint = sprintRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sprint not found with id: " + id));

        sprintRepository.delete(sprint);
    }
}
