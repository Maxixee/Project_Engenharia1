package br.com.ifba.eng1.domain.service;

import br.com.ifba.eng1.domain.dto.ProductBacklogDTO;
import br.com.ifba.eng1.domain.dto.ProductBacklogItemDTO;
import br.com.ifba.eng1.domain.entities.ProductBacklog;
import br.com.ifba.eng1.domain.entities.ProductBacklogItem;
import br.com.ifba.eng1.domain.exception.EntityNotFoundException;
import br.com.ifba.eng1.domain.exception.InvalidRegistrationInformationException;
import br.com.ifba.eng1.domain.exception.ResourceNotFoundException;
import br.com.ifba.eng1.domain.repository.ProductBacklogItemRepository;
import br.com.ifba.eng1.domain.repository.ProductBacklogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProductBacklogService {

    private final ProductBacklogRepository productBacklogRepository;

    @Transactional
    public ProductBacklogDTO save(ProductBacklogDTO dto) {
        if (dto.getTitle().isBlank() || dto.getTitle().isEmpty()){
            throw new InvalidRegistrationInformationException("Invalid Backlog details. Backlog not created.");
        }

        ProductBacklog entity = new ProductBacklog();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setCreatedDate(LocalDate.now());
        entity = productBacklogRepository.save(entity);

        return new ProductBacklogDTO(entity);
    }

    @Transactional(readOnly = true)
    public Page<ProductBacklogDTO> findAll(Pageable pageable) {
        Page<ProductBacklog> page = productBacklogRepository.findAll(pageable);

        return page.map(ProductBacklogDTO::new);
    }

    @Transactional(readOnly = true)
    public ProductBacklogDTO findById(Long id) {
        ProductBacklog entity = productBacklogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Backlog not found"));
        return new ProductBacklogDTO(entity);
    }

    @Transactional
    public ProductBacklogDTO update(Long id, ProductBacklogDTO dto) {

        try {
            ProductBacklog entity = productBacklogRepository.getReferenceById(id);
            entity.setTitle(dto.getTitle());
            entity.setDescription(dto.getDescription());
            entity = productBacklogRepository.save(entity);
            return new ProductBacklogDTO(entity);
        }catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found");
        }

    }

}
