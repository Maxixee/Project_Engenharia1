package br.com.ifba.eng1.domain.dto.mapper;

import br.com.ifba.eng1.domain.dto.ProductBacklogDTO;
import br.com.ifba.eng1.domain.entities.ProductBacklog;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductBacklogMapper {

    /*public static ProductBacklog toProductBacklog(ProductBacklogDTO dto) {
        return new ModelMapper().map(dto, ProductBacklog.class);
    }

    public static ProductBacklogResponseDTO toDTO(ProductBacklog productBacklog) {
        return new ModelMapper().map(productBacklog, ProductBacklogResponseDTO.class);
    }*/

}
