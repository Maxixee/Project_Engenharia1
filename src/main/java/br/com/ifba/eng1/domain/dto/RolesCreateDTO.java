package br.com.ifba.eng1.domain.dto;


import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RolesCreateDTO {
    private String name;
}