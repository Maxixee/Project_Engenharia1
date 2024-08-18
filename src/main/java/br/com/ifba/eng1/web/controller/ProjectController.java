package br.com.ifba.eng1.web.controller;


import br.com.ifba.eng1.domain.dto.*;
import br.com.ifba.eng1.domain.dto.mapper.PageableMapper;
import br.com.ifba.eng1.domain.dto.mapper.ProjectMapper;
import br.com.ifba.eng1.domain.entities.Project;
import br.com.ifba.eng1.domain.repository.projection.ProjectProjection;
import br.com.ifba.eng1.domain.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/save")
    public ResponseEntity<ProjectResponseDto> save(@RequestBody ProjectCreateDto dto){
        Project project = ProjectMapper.toProject(dto);
        this.projectService.save(project);

        return ResponseEntity.ok(ProjectMapper.toDto(project));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProjectResponseDto> update(@PathVariable Long id, @RequestBody ProjectCreateDto dto) {
        Project updatedProject = ProjectMapper.toProject(dto);
        Project project = this.projectService.update(id, updatedProject);
        return ResponseEntity.ok(ProjectMapper.toDto(project));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> getById(@PathVariable Long id) {
        Project project = this.projectService.findById(id);

        return ResponseEntity.ok(ProjectMapper.toDto(project));
    }

    @GetMapping
    public ResponseEntity<PageableDto> getAll(@PageableDefault(size = 5, sort = {"id"}) Pageable pageable) {
        Page<ProjectProjection> projects = this.projectService.findAll(pageable);
        return ResponseEntity.ok(PageableMapper.toDto(projects));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        this.projectService.delete(id);
        return ResponseEntity.ok("Project deleted");
    }
}
