package br.com.ifba.eng1.domain.dto.mapper;

import br.com.ifba.eng1.domain.dto.UsersCreateDto;
import br.com.ifba.eng1.domain.dto.UsersResponseDto;
import br.com.ifba.eng1.domain.entities.Users;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UsersMapper {

    public static Users toUser(UsersCreateDto dto) {
        return new ModelMapper().map(dto, Users.class);
    }

    public static UsersResponseDto toDto(Users user) {
        return new ModelMapper().map(user, UsersResponseDto.class);
    }
}
