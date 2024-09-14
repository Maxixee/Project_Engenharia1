package br.com.ifba.eng1.domain.dto.mapper;

import br.com.ifba.eng1.domain.dto.SprintBacklogViewDTO;
import br.com.ifba.eng1.domain.entities.SprintBacklog;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SprintBacklogMapper {

    public static SprintBacklogViewDTO toDTO(SprintBacklog sprintBacklog) {
        ModelMapper modelMapper = new ModelMapper();
        SprintBacklogViewDTO sprintBacklogDTO = modelMapper.map(sprintBacklog, SprintBacklogViewDTO.class);

        sprintBacklogDTO.setIdSprint(sprintBacklog.getIdSprint().getId());
        sprintBacklogDTO.setSprintName(sprintBacklog.getIdSprint().getName());

        return sprintBacklogDTO;
    }
}
