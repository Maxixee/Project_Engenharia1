package br.com.ifba.eng1.domain.service;

import br.com.ifba.eng1.domain.dto.ProductBacklogDTO;
import br.com.ifba.eng1.domain.dto.ProductBacklogItemDTO;
import br.com.ifba.eng1.domain.entities.ProductBacklog;
import br.com.ifba.eng1.domain.entities.ProductBacklogItem;
import br.com.ifba.eng1.domain.exception.InvalidRegistrationInformationException;
import br.com.ifba.eng1.domain.exception.ResourceNotFoundException;
import br.com.ifba.eng1.domain.repository.ProductBacklogItemRepository;
import br.com.ifba.eng1.domain.repository.ProductBacklogRepository;
import br.com.ifba.eng1.domain.repository.projection.ProductBacklogItemProjection;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProductBacklogItemService {

    private final ProductBacklogItemRepository productBacklogItemRepository;
    private final ProductBacklogRepository productBacklogRepository;

    @Transactional
    public ProductBacklogItemDTO save(Long backlogId, ProductBacklogItemDTO dto) {
        if (dto.getDescription().isEmpty() || dto.getStartDate().toString().isBlank()
            || dto.getEndDate().toString().isBlank()){
            throw new InvalidRegistrationInformationException("Invalid Backlog Item details. Backlog Item not created.");
        }

        ProductBacklogItem entity = new ProductBacklogItem();
        copyDtoToEntity(dto, entity, backlogId);
        entity = productBacklogItemRepository.save(entity);

        return new ProductBacklogItemDTO(entity);
    }

    @Transactional
    public ProductBacklogItemDTO update(Long backlogId, Long id, ProductBacklogItemDTO dto) {
        try {
            ProductBacklogItem entity = productBacklogItemRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity, backlogId);
            entity = productBacklogItemRepository.save(entity);
            return new ProductBacklogItemDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Product Backlog Item not found");
        }

    }

    @Transactional(readOnly = true)
    public List<ProductBacklogItemProjection> findAll(Long backlogId) {
        return productBacklogItemRepository.findAllByBacklogIdNative(backlogId);
    }

    @Transactional(readOnly = true)
    public ProductBacklogItemDTO findById(Long backlogId, Long id) {

        if (!productBacklogRepository.existsById(backlogId)) {
            throw new ResourceNotFoundException("Backlog not found");
        }

        ProductBacklogItem entity = productBacklogItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Backlog Item not found"));
        return new ProductBacklogItemDTO(entity);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id, Long backlogId) {

        if (!productBacklogRepository.existsById(backlogId)) {
            throw new ResourceNotFoundException("Backlog not found");
        }

        if (!productBacklogItemRepository.existsById(id)) {
            throw new ResourceNotFoundException("Backlog Item not found");
        }

        productBacklogItemRepository.deleteById(id);
    }

    private void copyDtoToEntity(ProductBacklogItemDTO dto, ProductBacklogItem entity, Long backlogId) {
        entity.setDescription(dto.getDescription());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setBacklog(new ProductBacklog(backlogId, null, null, null, null));
    }

}
