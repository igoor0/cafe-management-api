package pl.obrona.managementapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.obrona.managementapi.exception.NotFoundException;
import pl.obrona.managementapi.mapper.ProductComponentMapper;
import pl.obrona.managementapi.model.ProductComponent;
import pl.obrona.managementapi.model.command.CreateProductComponentCommand;
import pl.obrona.managementapi.model.dto.ProductComponentDto;
import pl.obrona.managementapi.repository.ProductComponentRepository;

import java.util.List;

import static pl.obrona.managementapi.mapper.ProductComponentMapper.mapFromCommand;
import static pl.obrona.managementapi.mapper.ProductComponentMapper.mapToDto;

@Service
@RequiredArgsConstructor
public class ProductComponentService {
    private final ProductComponentRepository productComponentRepository;

    public ProductComponentDto create(CreateProductComponentCommand command) {
        ProductComponent productComponent = productComponentRepository.save(mapFromCommand(command));
        return mapToDto(productComponent);
    }

    public List<ProductComponentDto> getAll() {
        return productComponentRepository.findAll().stream()
                .map(ProductComponentMapper::mapToDto)
                .toList();
    }

    public ProductComponentDto getById(Long id) {
        ProductComponent productComponent = productComponentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product component not found with id: " + id));
        return mapToDto(productComponent);
    }

    public void deleteById(Long id) {
        productComponentRepository.deleteById(id);
    }
}
