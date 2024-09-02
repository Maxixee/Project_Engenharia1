package br.com.ifba.eng1.web.controller;

import br.com.ifba.eng1.domain.dto.ProductBacklogDTO;
import br.com.ifba.eng1.domain.dto.ProductBacklogItemDTO;
import br.com.ifba.eng1.domain.entities.ProductBacklogItem;
import br.com.ifba.eng1.domain.repository.projection.ProductBacklogItemProjection;
import br.com.ifba.eng1.domain.service.ProductBacklogItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/backlogs/{backlogId}/backlog-items")
public class ProductBacklogItemController {

    private final ProductBacklogItemService productBacklogItemService;

    @PostMapping("/save")
    public ResponseEntity<ProductBacklogItemDTO> save(@PathVariable Long backlogId, @RequestBody ProductBacklogItemDTO dto){
        dto = productBacklogItemService.save(backlogId, dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductBacklogItemDTO> update(@PathVariable Long backlogId, @PathVariable Long id, @RequestBody ProductBacklogItemDTO dto) {
        dto = productBacklogItemService.update(backlogId, id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping()
    public ResponseEntity<List<ProductBacklogItemProjection>> findAll(@RequestParam(value = "page", defaultValue = "0")Integer page,
                                                                      @RequestParam(value = "size", defaultValue = "10")Integer size,
                                                                      @PathVariable Long backlogId){
        List<ProductBacklogItemProjection> list = productBacklogItemService.findAll(backlogId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductBacklogItemDTO> findById(@PathVariable Long backlogId, @PathVariable Long id) {
        ProductBacklogItemDTO dto = productBacklogItemService.findById(backlogId, id);

        return ResponseEntity.ok(dto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long backlogId, @PathVariable Long id) {
        productBacklogItemService.delete(id, backlogId);
        return ResponseEntity.noContent().build();
    }

}
