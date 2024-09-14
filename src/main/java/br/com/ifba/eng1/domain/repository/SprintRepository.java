package br.com.ifba.eng1.domain.repository;

import br.com.ifba.eng1.domain.entities.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SprintRepository extends JpaRepository<Sprint, Long> {

}
