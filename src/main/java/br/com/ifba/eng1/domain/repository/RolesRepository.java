package br.com.ifba.eng1.domain.repository;

import br.com.ifba.eng1.domain.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository  extends JpaRepository<Roles, Long> {
}
