package br.com.ifba.eng1.domain.dto;

import br.com.ifba.eng1.domain.entities.Project;
import br.com.ifba.eng1.domain.entities.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TasksCreateDto {
    private String description;
    private String priority;
    private Project project;
    private Users inCharge;
}
