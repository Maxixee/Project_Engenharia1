package br.com.ifba.eng1.domain.dto;


import br.com.ifba.eng1.domain.entities.ProductBacklogItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductBacklogItemDTO {

    private Long id;
    private String description;
    private Date startDate;
    private Date endDate;
    private Long backlogId;

    public ProductBacklogItemDTO(ProductBacklogItem entity) {
        id = entity.getId();
        description = entity.getDescription();
        startDate = entity.getStartDate();
        endDate = entity.getEndDate();
        backlogId = entity.getBacklog().getId();
    }

}
