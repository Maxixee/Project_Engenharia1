package br.com.ifba.eng1.domain.service;

import br.com.ifba.eng1.domain.entities.Project;
import br.com.ifba.eng1.domain.entities.Tasks;
import br.com.ifba.eng1.domain.entities.Users;
import br.com.ifba.eng1.domain.exception.EntityNotFoundException;
import br.com.ifba.eng1.domain.exception.InvalidRegistrationInformationException;
import br.com.ifba.eng1.domain.exception.ProjectAlreadyExistsException;
import br.com.ifba.eng1.domain.exception.UnauthorizedActionException;
import br.com.ifba.eng1.domain.repository.ProjectRepository;
import br.com.ifba.eng1.domain.repository.TasksRepository;
import br.com.ifba.eng1.domain.repository.UsersRepository;
import br.com.ifba.eng1.domain.repository.projection.ProjectProjection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.config.Task;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final TasksRepository tasksRepository;
    private final UsersService usersService;

    @Transactional(propagation = Propagation.REQUIRED)
    public Project save(Project project){

        Optional<Project> projectOptional = this.projectRepository.findByName(project.getName());
        if(projectOptional.isPresent()){
            throw new ProjectAlreadyExistsException(String.format("Project with name %s already exists", project.getName()));
        }
        if(project.getName().isBlank() || project.getDescription().isBlank()){
            throw new InvalidRegistrationInformationException("Invalid registration credentials (Name or Description)");
        }

        log.info("Saving project: {}", project);
        return this.projectRepository.save(project);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Project update(Long id, Project updatedProject){
        Project existingProject = this.findById(id);
        if(updatedProject.getName().isBlank() || updatedProject.getDescription().isBlank()){
            throw new InvalidRegistrationInformationException("Invalid update credentials (Name or Description)");
        }

        existingProject.setName(updatedProject.getName());
        existingProject.setDescription(updatedProject.getDescription());

        log.info("Updating project id={} : {}", id, existingProject);
        return this.projectRepository.save(existingProject);
    }

    @Transactional(readOnly = true)
    public Project findById(Long id){
        return this.projectRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Project with id= %s not found", id))
        );
    }

    @Transactional(readOnly = true)
    public Page<ProjectProjection> findAll(Pageable pageable){
        return this.projectRepository.findAllPageable(pageable);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Long id){
        Project project = this.findById(id);
        this.projectRepository.delete(project);
        log.info("Deleting project id= {} : {}", id, project);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Tasks assignTaskToMember(Long projectId, Long userId, Tasks task, Long requestingUserId) {
        Project project = this.findById(projectId);
        Users user = usersService.findById(userId);
        Users requestingUser = usersService.findById(requestingUserId);

        if (!project.getManager().getId().equals(requestingUser.getId())) {
            throw new UnauthorizedActionException("Only the project manager can assign tasks.");
        }

        if (!project.getMembers().contains(user)) {
            throw new IllegalArgumentException("User is not a member of the project");
        }

        task.setInCharge(user);
        task.setProject(project);

        log.info("Assigning task '{}' to user '{}' in project '{}'", task.getDescription(), user.getName(), project.getName());
        return tasksRepository.save(task);
    }
}
