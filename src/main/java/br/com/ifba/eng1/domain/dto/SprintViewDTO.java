package br.com.ifba.eng1.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SprintViewDTO {
    private Long id;
    private String projectName;
    private String name;
    private String startDate;
    private String endDate;
    private boolean status;
}
