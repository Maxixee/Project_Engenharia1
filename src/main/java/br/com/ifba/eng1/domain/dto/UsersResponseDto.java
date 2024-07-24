package br.com.ifba.eng1.domain.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersResponseDto {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String password;
}
