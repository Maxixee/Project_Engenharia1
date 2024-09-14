package br.com.ifba.eng1.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SprintBacklogViewDTO {

    private Long id;
    private Long idSprint;
    private String sprintName;
    private String inCharge;
    private String description;
    private String priority;
    private boolean completed;
}
