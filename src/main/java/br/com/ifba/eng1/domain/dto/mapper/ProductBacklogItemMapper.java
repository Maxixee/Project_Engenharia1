package br.com.ifba.eng1.domain.dto.mapper;

import br.com.ifba.eng1.domain.dto.ProductBacklogItemDTO;
import br.com.ifba.eng1.domain.entities.ProductBacklogItem;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductBacklogItemMapper {

    public static ProductBacklogItem toProductBacklogItem(ProductBacklogItemDTO dto) {
        return new ModelMapper().map(dto, ProductBacklogItem.class);
    }

    public static ProductBacklogItemDTO toDTO(ProductBacklogItem productBacklogItem) {
        return new ModelMapper().map(productBacklogItem, ProductBacklogItemDTO.class);
    }

}
