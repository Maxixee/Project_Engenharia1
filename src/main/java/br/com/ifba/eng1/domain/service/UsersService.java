package br.com.ifba.eng1.domain.service;

import br.com.ifba.eng1.domain.dto.AuthDto;
import br.com.ifba.eng1.domain.entities.Users;
import br.com.ifba.eng1.domain.exception.EntityNotFoundException;
import br.com.ifba.eng1.domain.exception.InvalidRegistrationInformationException;
import br.com.ifba.eng1.domain.exception.UserAlreadyExistsException;
import br.com.ifba.eng1.domain.exception.UsernameNotFoundException;
import br.com.ifba.eng1.domain.repository.UsersRepository;
import br.com.ifba.eng1.domain.repository.projection.UsersProjection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class UsersService implements UserDetailsService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(propagation = Propagation.REQUIRED)
    public Users save(Users user){

        Optional<Users> usersOptional = this.usersRepository.findByName(user.getName());
        if(usersOptional.isPresent()){
            throw new UserAlreadyExistsException("User already exists");
        }
        if(user.getName().isBlank() || user.getPassword().isBlank() || user.getEmail().isBlank()){
            throw new InvalidRegistrationInformationException("Invalid registration credentials (Name, Email or Password)");
        }

        user = user.toBuilder().password(this.passwordEncoder.encode(user.getPassword())).build();

        this.usersRepository.save(user);

        log.info("Saving user: {}", user);
        return user;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Users update(Long id, Users updatedUser) {
        Users existingUser = this.findById(id);
        if (updatedUser.getName().isBlank() || updatedUser.getLastName().isBlank() ||updatedUser.getEmail().isBlank() || updatedUser.getPassword().isBlank()) {
            throw new InvalidRegistrationInformationException("Invalid update credentials (Name, Email or Password)");
        }

        existingUser.setName(updatedUser.getName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(this.passwordEncoder.encode(updatedUser.getPassword()));

        this.usersRepository.save(existingUser);
        log.info("Updating user id={}: {}", id, existingUser);
        return existingUser;
    }

    @Transactional(readOnly = true)
    public Users findByEmail(String email){
        return this.usersRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Not found"));
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
        Users user = this.findById(id);// Reutiliza o método findById para verificar se o personagem existe
        this.usersRepository.delete(user);
        log.info("Deleting user id={}: {}", id, user);
    }

    public AuthDto auth(AuthDto authDto) {
        Users users = this.findByEmail(authDto.getEmail());

        if (!this.passwordEncoder.matches(authDto.getPassword(), users.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        StringBuilder password = new StringBuilder().append(users.getEmail()).append(":").append(users.getPassword());

        return AuthDto.builder().email(users.getEmail()).token(
                Base64.getEncoder().withoutPadding().encodeToString(password.toString().getBytes())
        ).id(users.getId()).build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> usersOptional = this.usersRepository.findByEmail(username);

        return usersOptional.map(users -> new User(users.getEmail(), users.getPassword(), new ArrayList<GrantedAuthority>()))
                .orElseThrow(() -> new UsernameNotFoundException("User not found" + username));
    }
}
