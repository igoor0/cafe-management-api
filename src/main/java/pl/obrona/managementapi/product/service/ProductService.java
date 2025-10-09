package pl.obrona.managementapi.product.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.obrona.managementapi.common.exception.NotFoundException;
import pl.obrona.managementapi.ingredient.model.Ingredient;
import pl.obrona.managementapi.ingredient.repository.IngredientRepository;
import pl.obrona.managementapi.product.mapper.ProductMapper;
import pl.obrona.managementapi.product.model.Product;
import pl.obrona.managementapi.product.model.ProductComponent;
import pl.obrona.managementapi.product.model.command.ComponentCommand;
import pl.obrona.managementapi.product.model.command.CreateProductCommand;
import pl.obrona.managementapi.product.model.dto.ProductDto;
import pl.obrona.managementapi.product.repository.ProductRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static pl.obrona.managementapi.product.mapper.ProductMapper.mapToDto;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final IngredientRepository ingredientRepository;

    @Transactional
    public ProductDto create(CreateProductCommand command) {
//        Product product = ProductMapper.mapFromCommand(command);
        Product product = Product.builder()
                .name(command.getName())
                .price(command.getPrice())
                .takeaway(command.isTakeaway())
                .build();

        Set<ProductComponent> productComponents = new HashSet<>();
        for (ComponentCommand c : command.getProductComponents()) {
            Ingredient ingredient = ingredientRepository.findById(c.getIngredientId())
                    .orElseThrow(() -> new NotFoundException("Ingredient not found with id: " + c.getIngredientId()));

            ProductComponent productComponent = ProductComponent.builder()
                    .product(product)
                    .ingredient(ingredient)
                    .amount(c.getAmount())
                    .build();

            productComponents.add(productComponent);
        }
        product.setProductComponents(productComponents);

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
