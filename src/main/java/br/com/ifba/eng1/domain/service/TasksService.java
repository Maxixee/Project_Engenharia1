package br.com.ifba.eng1.domain.service;

import br.com.ifba.eng1.domain.entities.Tasks;
import br.com.ifba.eng1.domain.exception.EntityNotFoundException;
import br.com.ifba.eng1.domain.exception.InvalidRegistrationInformationException;
import br.com.ifba.eng1.domain.repository.TasksRepository;
import br.com.ifba.eng1.domain.repository.projection.TasksProjection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class TasksService {

    private final TasksRepository tasksRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public Tasks save(Tasks task){
        if(task.getDescription().isBlank() || task.getPriority().isBlank()){
            throw new InvalidRegistrationInformationException("Invalid registration credentials (Description or Priority)");
        }

        log.info("Saving task: {}", task);
        return this.tasksRepository.save(task);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Tasks update(Long id, Tasks updatedTask){
        Tasks existingTask = this.findById(id);
        if(updatedTask.getDescription().isBlank() || updatedTask.getPriority().isBlank()){
            throw new InvalidRegistrationInformationException("Invalid update credentials (Description or Priority)");
        }

        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setPriority(updatedTask.getPriority());

        log.info("Updating task id={} : {}", id, existingTask);
        return this.tasksRepository.save(existingTask);
    }

    @Transactional(readOnly = true)
    public Tasks findById(Long id){
        return this.tasksRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Task with id= %s not found", id))
        );
    }

    @Transactional(readOnly = true)
    public Page<TasksProjection> findAll(Pageable pageable){
        return this.tasksRepository.findAllPageable(pageable);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Long id){
        Tasks task = this.findById(id);
        this.tasksRepository.delete(task);
        log.info("Deleting task id= {} : {}", id, task);
    }
}

