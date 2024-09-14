package br.com.ifba.eng1.domain.repository;

import br.com.ifba.eng1.domain.entities.Sprint;
import br.com.ifba.eng1.domain.entities.SprintBacklog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SprintBacklogRepository extends JpaRepository<SprintBacklog, Long> {
}
