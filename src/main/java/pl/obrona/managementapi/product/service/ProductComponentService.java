package pl.obrona.managementapi.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.obrona.managementapi.product.mapper.ProductComponentMapper;
import pl.obrona.managementapi.product.model.dto.ProductComponentDto;
import pl.obrona.managementapi.product.repository.ProductComponentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductComponentService {
    private final ProductComponentRepository productComponentRepository;

    public List<ProductComponentDto> getAll() {
        return productComponentRepository.findAll().stream()
                .map(ProductComponentMapper::mapToDto)
                .toList();
    }
}
