package br.com.ifba.eng1.domain.dto;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersResponseDto {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private LocalDate birthDate;
    private String cellphoneNumber;
}
