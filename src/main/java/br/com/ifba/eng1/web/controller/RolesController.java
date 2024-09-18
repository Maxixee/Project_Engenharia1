package br.com.ifba.eng1.web.controller;

import br.com.ifba.eng1.domain.dto.RolesCreateDTO;
import br.com.ifba.eng1.domain.dto.RolesResponseDTO;
import br.com.ifba.eng1.domain.dto.mapper.RolesMapper;
import br.com.ifba.eng1.domain.entities.Roles;
import br.com.ifba.eng1.domain.service.RolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/roles")
public class RolesController {

    private final RolesService rolesService;

    @PostMapping("/save")
    public ResponseEntity<RolesResponseDTO> save(@RequestBody RolesCreateDTO dto) {
        Roles role = RolesMapper.toRole(dto); // Chamada direta da classe
        Roles savedRole = rolesService.save(role);
        return ResponseEntity.ok(RolesMapper.toDTO(savedRole)); // Chamada direta da classe
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolesResponseDTO> getById(@PathVariable Long id) {
        Roles role = rolesService.findById(id);
        return ResponseEntity.ok(RolesMapper.toDTO(role)); // Chamada direta da classe
    }

    @GetMapping
    public ResponseEntity<Iterable<RolesResponseDTO>> getAll() {
        Iterable<Roles> roles = rolesService.findAll();
        List<RolesResponseDTO> dtos = StreamSupport.stream(roles.spliterator(), false)
                .map(RolesMapper::toDTO) // Chamada direta da classe
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        rolesService.delete(id);
        return ResponseEntity.ok("Role deleted");
    }
}