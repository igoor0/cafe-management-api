package pl.obrona.managementapi.mapper;

import lombok.experimental.UtilityClass;
import pl.obrona.managementapi.model.command.CreateProductCommand;
import pl.obrona.managementapi.model.dto.ProductDto;
import pl.obrona.managementapi.model.product.Product;
import pl.obrona.managementapi.model.product.ProductComponent;

import java.util.stream.Collectors;

@UtilityClass
public class ProductMapper {
    public static Product mapFromCommand(CreateProductCommand command) {
        return Product.builder()
                .name(command.getName())
                .price(command.getPrice())
                .takeaway(command.isTakeaway())
                .build();
    }

    public static ProductDto mapToDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .productComponentIds(product.getProductComponents().stream()
                        .map(ProductComponent::getId)
                        .collect(Collectors.toSet()))
                .takeaway(product.isTakeaway())
                .build();
    }
}
