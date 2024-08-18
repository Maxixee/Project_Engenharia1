package br.com.ifba.eng1.domain.dto.mapper;


import br.com.ifba.eng1.domain.dto.TasksCreateDto;
import br.com.ifba.eng1.domain.dto.TasksResponseDto;
import br.com.ifba.eng1.domain.entities.Tasks;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TasksMapper {

    public static Tasks toTasks(TasksCreateDto dto) {
        return new ModelMapper().map(dto, Tasks.class);
    }

    public static TasksResponseDto toDto(Tasks tasks) {
        return new ModelMapper().map(tasks, TasksResponseDto.class);
    }
}
