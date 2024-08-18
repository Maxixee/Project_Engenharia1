package br.com.ifba.eng1.domain.dto.mapper;


import br.com.ifba.eng1.domain.dto.ProjectCreateDto;
import br.com.ifba.eng1.domain.dto.ProjectResponseDto;
import br.com.ifba.eng1.domain.entities.Project;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProjectMapper {

    public static Project toProject(ProjectCreateDto dto) {
        return new ModelMapper().map(dto, Project.class);
    }

    public static ProjectResponseDto toDto(Project project) {
        return new ModelMapper().map(project, ProjectResponseDto.class);
    }
}
