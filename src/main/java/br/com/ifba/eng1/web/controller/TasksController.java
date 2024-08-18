package br.com.ifba.eng1.web.controller;

import br.com.ifba.eng1.domain.dto.TasksCreateDto;
import br.com.ifba.eng1.domain.dto.TasksResponseDto;
import br.com.ifba.eng1.domain.entities.Tasks;
import br.com.ifba.eng1.domain.repository.projection.TasksProjection;
import br.com.ifba.eng1.domain.service.TasksService;
import br.com.ifba.eng1.domain.dto.PageableDto;
import br.com.ifba.eng1.domain.dto.mapper.TasksMapper;
import br.com.ifba.eng1.domain.dto.mapper.PageableMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TasksController {

    private final TasksService tasksService;

    @PostMapping("/save")
    public ResponseEntity<TasksResponseDto> save(@RequestBody TasksCreateDto dto){
        Tasks task = TasksMapper.toTasks(dto);
        Tasks savedTask = this.tasksService.save(task);

        return ResponseEntity.ok(TasksMapper.toDto(savedTask));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TasksResponseDto> update(@PathVariable Long id, @RequestBody TasksCreateDto dto) {
        Tasks updatedTask = TasksMapper.toTasks(dto);
        Tasks task = this.tasksService.update(id, updatedTask);
        return ResponseEntity.ok(TasksMapper.toDto(task));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TasksResponseDto> getById(@PathVariable Long id) {
        Tasks task = this.tasksService.findById(id);

        return ResponseEntity.ok(TasksMapper.toDto(task));
    }

    @GetMapping
    public ResponseEntity<PageableDto> getAll(@PageableDefault(size = 5, sort = {"id"}) Pageable pageable) {
        Page<TasksProjection> tasks = this.tasksService.findAll(pageable);
        return ResponseEntity.ok(PageableMapper.toDto(tasks));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        this.tasksService.delete(id);
        return ResponseEntity.ok("Task deleted");
    }
}

