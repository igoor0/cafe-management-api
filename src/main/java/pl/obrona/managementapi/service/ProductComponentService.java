package pl.obrona.managementapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.obrona.managementapi.mapper.ProductComponentMapper;
import pl.obrona.managementapi.model.dto.ProductComponentDto;
import pl.obrona.managementapi.repository.ProductComponentRepository;

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
