package br.com.ifba.eng1.domain.repository;

import br.com.ifba.eng1.domain.entities.Users;
import br.com.ifba.eng1.domain.repository.projection.UsersProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query("select c from Users c")
    Page<UsersProjection> findAllPageable(Pageable pageable);

    public Optional<Users> findByName(String name);
}
