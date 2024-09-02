package br.com.ifba.eng1.web.controller;

import br.com.ifba.eng1.domain.dto.ProductBacklogDTO;
import br.com.ifba.eng1.domain.dto.mapper.ProductBacklogMapper;
import br.com.ifba.eng1.domain.entities.ProductBacklog;
import br.com.ifba.eng1.domain.service.ProductBacklogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/backlogs")
public class ProductBacklogController {

    private final ProductBacklogService productBacklogService;

    @PostMapping("/save")
    public ResponseEntity<ProductBacklogDTO> save(@RequestBody ProductBacklogDTO dto){
        dto = productBacklogService.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping()
    public ResponseEntity<Page<ProductBacklogDTO>> findAll(@RequestParam(value = "page", defaultValue = "0")Integer page,
                                                          @RequestParam(value = "size", defaultValue = "10")Integer size){
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductBacklogDTO> dto = productBacklogService.findAll(pageable);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductBacklogDTO> findById(@PathVariable Long id) {
        ProductBacklogDTO dto = productBacklogService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductBacklogDTO> update(@PathVariable Long id, @RequestBody ProductBacklogDTO dto) {
        dto = productBacklogService.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

}
