package br.com.ifba.eng1.web.controller;

import br.com.ifba.eng1.domain.dto.SprintBacklogCreateDTO;
import br.com.ifba.eng1.domain.dto.SprintBacklogViewDTO;
import br.com.ifba.eng1.domain.service.SprintBacklogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sprintBacklog")
public class SprintBacklogController {
    @Autowired
    private SprintBacklogService sprintBacklogService;

    @PostMapping("/save")
    public ResponseEntity<SprintBacklogViewDTO> createSprintBacklog(@Valid @RequestBody SprintBacklogCreateDTO sprintBacklogDTO) {
        SprintBacklogViewDTO createdSprintBacklog = sprintBacklogService.createSprintBacklog(sprintBacklogDTO);
        return new ResponseEntity<>(createdSprintBacklog, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SprintBacklogViewDTO> getSprintBacklogById(@PathVariable Long id) {
        SprintBacklogViewDTO sprintBacklog = sprintBacklogService.getSprintBacklogById(id);
        return ResponseEntity.ok(sprintBacklog);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<SprintBacklogViewDTO>> getAllSprintBacklogs() {
        List<SprintBacklogViewDTO> sprintBacklogs = sprintBacklogService.getAllSprintBacklogs();
        return ResponseEntity.ok(sprintBacklogs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SprintBacklogViewDTO> updateSprintBacklog(@PathVariable Long id, @Valid @RequestBody SprintBacklogCreateDTO sprintBacklogDTO) {
        SprintBacklogViewDTO updatedSprintBacklog = sprintBacklogService.updateSprintBacklog(id, sprintBacklogDTO);
        return ResponseEntity.ok(updatedSprintBacklog);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSprintBacklog(@PathVariable Long id) {
        sprintBacklogService.deleteSprintBacklog(id);
        return ResponseEntity.noContent().build();
    }
}
