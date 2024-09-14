package br.com.ifba.eng1.domain.service;

import br.com.ifba.eng1.domain.dto.SprintBacklogCreateDTO;
import br.com.ifba.eng1.domain.dto.SprintBacklogViewDTO;
import br.com.ifba.eng1.domain.dto.mapper.SprintBacklogMapper;
import br.com.ifba.eng1.domain.entities.Sprint;
import br.com.ifba.eng1.domain.entities.SprintBacklog;
import br.com.ifba.eng1.domain.exception.ResourceNotFoundException;
import br.com.ifba.eng1.domain.repository.SprintBacklogRepository;
import br.com.ifba.eng1.domain.repository.SprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SprintBacklogService {
    @Autowired
    private SprintBacklogRepository sprintBacklogRepository;

    @Autowired
    private SprintRepository sprintRepository;

    public SprintBacklogViewDTO createSprintBacklog(SprintBacklogCreateDTO sprintBacklogDTO) {
        Sprint sprint = sprintRepository.findById(sprintBacklogDTO.getIdSprint())
                .orElseThrow(() -> new ResourceNotFoundException("Sprint not found"));

        SprintBacklog sprintBacklog = new SprintBacklog();
        sprintBacklog.setIdSprint(sprint);
        sprintBacklog.setInCharge(sprintBacklogDTO.getInCharge());
        sprintBacklog.setDescription(sprintBacklogDTO.getDescription());
        sprintBacklog.setPriority(sprintBacklogDTO.getPriority());
        sprintBacklog.setCompleted(sprintBacklogDTO.isCompleted());

        SprintBacklog savedSprintBacklog = sprintBacklogRepository.save(sprintBacklog);
        return SprintBacklogMapper.toDTO(savedSprintBacklog);
    }

    public SprintBacklogViewDTO getSprintBacklogById(Long id) {
        SprintBacklog sprintBacklog = sprintBacklogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SprintBacklog not found"));
        return SprintBacklogMapper.toDTO(sprintBacklog);
    }

    public List<SprintBacklogViewDTO> getAllSprintBacklogs() {
        return sprintBacklogRepository.findAll()
                .stream()
                .map(SprintBacklogMapper::toDTO)
                .collect(Collectors.toList());
    }

    public SprintBacklogViewDTO updateSprintBacklog(Long id, SprintBacklogCreateDTO sprintBacklogDTO) {
        SprintBacklog sprintBacklog = sprintBacklogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SprintBacklog not found"));

        Sprint sprint = sprintRepository.findById(sprintBacklogDTO.getIdSprint())
                .orElseThrow(() -> new ResourceNotFoundException("Sprint not found"));

        sprintBacklog.setIdSprint(sprint);
        sprintBacklog.setInCharge(sprintBacklogDTO.getInCharge());
        sprintBacklog.setDescription(sprintBacklogDTO.getDescription());
        sprintBacklog.setPriority(sprintBacklogDTO.getPriority());
        sprintBacklog.setCompleted(sprintBacklogDTO.isCompleted());

        SprintBacklog updatedSprintBacklog = sprintBacklogRepository.save(sprintBacklog);
        return SprintBacklogMapper.toDTO(updatedSprintBacklog);
    }

    public void deleteSprintBacklog(Long id) {
        SprintBacklog sprintBacklog = sprintBacklogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SprintBacklog not found"));
        sprintBacklogRepository.delete(sprintBacklog);
    }
}
