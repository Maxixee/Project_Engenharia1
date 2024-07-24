package br.com.ifba.eng1.domain.service;

import br.com.ifba.eng1.domain.entities.Users;
import br.com.ifba.eng1.domain.exception.EntityNotFoundException;
import br.com.ifba.eng1.domain.exception.InvalidRegistrationInformationException;
import br.com.ifba.eng1.domain.exception.UserAlreadyExistsException;
import br.com.ifba.eng1.domain.repository.UsersRepository;
import br.com.ifba.eng1.domain.repository.projection.UsersProjection;
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
public class UsersService {

    private final UsersRepository usersRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public Users save(Users user){

        Optional<Users> usersOptional = this.usersRepository.findByName(user.getName());
        if(usersOptional.isPresent()){
            throw new UserAlreadyExistsException("User already exists");
        }
        if(user.getName().isBlank() || user.getPassword().isBlank() || user.getEmail().isBlank()){
            throw new InvalidRegistrationInformationException("Invalid registration credentials (Name, Email or Password)");
        }

        this.usersRepository.save(user);

        log.info("Saving user: {}", user);
        return user;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Users update(Long id, Users updatedUser) {
        Users existingUser = this.usersRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("User id=%s not found", id))
        );

        if (updatedUser.getName().isBlank() || updatedUser.getLastName().isBlank() ||updatedUser.getEmail().isBlank() || updatedUser.getPassword().isBlank()) {
            throw new InvalidRegistrationInformationException("Invalid update credentials (Name, Email or Password)");
        }

        existingUser.setName(updatedUser.getName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());

        this.usersRepository.save(existingUser);
        log.info("Updating user id={}: {}", id, existingUser);
        return existingUser;
    }

    @Transactional(readOnly = true)
    public Users findById(Long id) {
        return usersRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("User id=%s not found", id))
        );
    }

    @Transactional(readOnly = true)
    public Page<UsersProjection> findAll(Pageable pageable) {
        return usersRepository.findAllPageable(pageable);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        Users user = this.findById(id); // Reutiliza o método findById para verificar se o personagem existe
        this.usersRepository.delete(user);
        log.info("Deleting user id={}: {}", id, user);
    }
}
