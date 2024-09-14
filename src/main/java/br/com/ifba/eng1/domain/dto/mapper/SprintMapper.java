package br.com.ifba.eng1.domain.dto.mapper;

import br.com.ifba.eng1.domain.dto.SprintViewDTO;
import br.com.ifba.eng1.domain.entities.Sprint;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import br.com.ifba.eng1.domain.dto.InviteViewDTO;
import br.com.ifba.eng1.domain.entities.Invite;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SprintMapper {
    public static SprintViewDTO toDTO(Sprint sprint) {
        ModelMapper modelMapper = new ModelMapper();
        SprintViewDTO sprintDTO = modelMapper.map(sprint, SprintViewDTO.class);

        sprintDTO.setProjectName(sprint.getIdProject().getName());
        sprintDTO.setStartDate(sprint.getStartDate());
        sprintDTO.setEndDate(sprint.getEndDate());
        sprintDTO.setStatus(sprint.isStatus());

        return sprintDTO;
    }
}
