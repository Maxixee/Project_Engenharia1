package br.com.ifba.eng1.domain.dto;


import br.com.ifba.eng1.domain.entities.Tasks;
import br.com.ifba.eng1.domain.entities.Users;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponseDto {

    private String name;
    private String description;
    private Users manager;
    private List<Users> members;
    private List<Tasks> tasks;
}
