package br.com.ifba.eng1.web.controller;

import br.com.ifba.eng1.domain.dto.AuthDto;
import br.com.ifba.eng1.domain.dto.PageableDto;
import br.com.ifba.eng1.domain.dto.UsersCreateDto;
import br.com.ifba.eng1.domain.dto.UsersResponseDto;
import br.com.ifba.eng1.domain.dto.mapper.PageableMapper;
import br.com.ifba.eng1.domain.dto.mapper.UsersMapper;
import br.com.ifba.eng1.domain.entities.Users;
import br.com.ifba.eng1.domain.repository.projection.UsersProjection;
import br.com.ifba.eng1.domain.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    @PostMapping("/save")
    public ResponseEntity<UsersResponseDto> save(@RequestBody UsersCreateDto dto){
        Users user = UsersMapper.toUser(dto);
        usersService.save(user);

        return ResponseEntity.ok(UsersMapper.toDto(user));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UsersResponseDto> update(@PathVariable Long id, @RequestBody UsersCreateDto dto) {
        Users updatedUser = UsersMapper.toUser(dto);
        Users user = usersService.update(id, updatedUser);
        return ResponseEntity.ok(UsersMapper.toDto(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersResponseDto> getById(@PathVariable Long id) {
        Users user = usersService.findById(id);

        return ResponseEntity.ok(UsersMapper.toDto(user));
    }

    @GetMapping
    public ResponseEntity<PageableDto> getAll(@PageableDefault(size = 5, sort = {"id"}) Pageable pageable) {
        Page<UsersProjection> users = usersService.findAll(pageable);
        return ResponseEntity.ok(PageableMapper.toDto(users));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        usersService.delete(id);
        return ResponseEntity.ok("User deleted");
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthDto> auth(@RequestBody AuthDto user){
        return ResponseEntity.ok(this.usersService.auth(user));
    }
}
