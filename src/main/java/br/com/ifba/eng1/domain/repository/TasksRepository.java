package br.com.ifba.eng1.domain.repository;

import br.com.ifba.eng1.domain.entities.Tasks;
import br.com.ifba.eng1.domain.repository.projection.TasksProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TasksRepository extends JpaRepository<Tasks, Long> {

    @Query("select c from Tasks c")
    Page<TasksProjection> findAllPageable(Pageable pageable);
}
