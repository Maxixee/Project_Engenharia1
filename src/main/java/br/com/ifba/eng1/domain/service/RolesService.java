package br.com.ifba.eng1.domain.service;

import br.com.ifba.eng1.domain.entities.Roles;
import br.com.ifba.eng1.domain.exception.EntityNotFoundException;
import br.com.ifba.eng1.domain.repository.RolesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Slf4j
@Service
public class RolesService {

    private final RolesRepository rolesRepository;

    @Transactional(readOnly = true)
    public Roles findById(Long id) {
        return this.rolesRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Role with id= %s not found", id))
        );
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Roles save(Roles role) {
        log.info("Saving role: {}", role);
        return this.rolesRepository.save(role);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        Roles role = this.findById(id);
        this.rolesRepository.delete(role);
        log.info("Deleting role id= {} : {}", id, role);
    }
}