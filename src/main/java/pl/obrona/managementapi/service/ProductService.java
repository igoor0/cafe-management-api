package pl.obrona.managementapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.obrona.managementapi.exception.NotFoundException;
import pl.obrona.managementapi.mapper.ProductMapper;
import pl.obrona.managementapi.model.command.CreateProductCommand;
import pl.obrona.managementapi.model.dto.ProductDto;
import pl.obrona.managementapi.model.product.Product;
import pl.obrona.managementapi.repository.ProductRepository;

import java.util.List;

import static pl.obrona.managementapi.mapper.ProductMapper.mapToDto;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductDto create(CreateProductCommand command) {
        Product product = ProductMapper.mapFromCommand(command);
        return mapToDto(productRepository.save(product));
    }

    public List<ProductDto> getAll() {
        return productRepository.findAll().stream()
                .map(ProductMapper::mapToDto)
                .toList();
    }

    public ProductDto getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found with id: " + id));
        return mapToDto(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
