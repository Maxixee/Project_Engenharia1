package br.com.ifba.eng1.domain.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SprintBacklogCreateDTO {
    @NotNull(message = "Sprint ID is required")
    private Long idSprint;

    @NotBlank(message = "In Charge is required")
    private String inCharge;

    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description should not exceed 500 characters")
    private String description;

    @NotBlank(message = "Priority is required")
    private String priority;

    private boolean completed;
}
