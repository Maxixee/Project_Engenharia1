package br.com.ifba.eng1.domain.dto;

import br.com.ifba.eng1.domain.entities.ProductBacklog;
import br.com.ifba.eng1.domain.entities.ProductBacklogItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductBacklogDTO {

    private Long id;
    private String title;
    private String description;
    private LocalDate createdDate;
    private List<ProductBacklogItemDTO> backlogItems = new ArrayList<>();

    public ProductBacklogDTO(ProductBacklog entity) {
        id = entity.getId();
        title = entity.getTitle();
        description = entity.getDescription();
        createdDate = entity.getCreatedDate();
        for (ProductBacklogItem item : entity.getBacklogItems()) {
            backlogItems.add(new ProductBacklogItemDTO(item));
        }
    }

}
