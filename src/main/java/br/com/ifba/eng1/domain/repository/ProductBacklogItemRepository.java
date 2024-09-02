package br.com.ifba.eng1.domain.repository;

import br.com.ifba.eng1.domain.entities.ProductBacklogItem;
import br.com.ifba.eng1.domain.repository.projection.ProductBacklogItemProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductBacklogItemRepository extends JpaRepository<ProductBacklogItem, Long> {

    @Query(value = """
                        SELECT * FROM backlog_items 
                        WHERE backlog_id = :backlogId
                   """, nativeQuery = true
            )
    List<ProductBacklogItemProjection> findAllByBacklogIdNative(@Param("backlogId") Long backlogId);

}
