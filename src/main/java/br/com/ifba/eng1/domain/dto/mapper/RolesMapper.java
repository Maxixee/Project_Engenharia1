package br.com.ifba.eng1.domain.dto.mapper;


import br.com.ifba.eng1.domain.dto.RolesCreateDTO;
import br.com.ifba.eng1.domain.dto.RolesResponseDTO;
import br.com.ifba.eng1.domain.entities.Roles;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RolesMapper {

    public static Roles toRole(RolesCreateDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        return modelMapper.map(dto, Roles.class);
    }

    public static RolesResponseDTO toDTO(Roles roles) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(roles, RolesResponseDTO.class);
    }

    public static Iterable<RolesResponseDTO> toDTOs(Iterable<Roles> roles) {
        List<RolesResponseDTO> dtos = new ArrayList<>();
        for (Roles role : roles) {
            dtos.add(toDTO(role));
        }
        return dtos;
    }
}
