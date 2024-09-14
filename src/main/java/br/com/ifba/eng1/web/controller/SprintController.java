package br.com.ifba.eng1.web.controller;

import br.com.ifba.eng1.domain.dto.SprintCreateDTO;
import br.com.ifba.eng1.domain.dto.SprintViewDTO;
import br.com.ifba.eng1.domain.service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/sprints")
public class SprintController {

    @Autowired
    private SprintService sprintService;

    @PostMapping("/save")
    public ResponseEntity<SprintViewDTO> save(@Valid @RequestBody SprintCreateDTO sprintDTO) {
        SprintViewDTO createdSprint = sprintService.save(sprintDTO);
        return new ResponseEntity<>(createdSprint, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SprintViewDTO> findById(@PathVariable Long id) {
        SprintViewDTO sprint = sprintService.findById(id);
        return ResponseEntity.ok(sprint);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<SprintViewDTO>> findAll() {
        List<SprintViewDTO> sprints = sprintService.findAll();
        return ResponseEntity.ok(sprints);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SprintViewDTO> update(@PathVariable Long id, @Valid @RequestBody SprintCreateDTO sprintDTO) {
        SprintViewDTO updatedSprint = sprintService.update(id, sprintDTO);
        return ResponseEntity.ok(updatedSprint);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        sprintService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
