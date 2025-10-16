package pl.obrona.managementapi.product.mapper;

import lombok.experimental.UtilityClass;
import pl.obrona.managementapi.product.model.Product;
import pl.obrona.managementapi.product.model.ProductComponent;
import pl.obrona.managementapi.product.model.dto.ProductDto;

@UtilityClass
public class ProductMapper {

    public static ProductDto mapToDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .productComponentIds(product.getProductComponents().stream()
                        .map(ProductComponent::getId)
                        .toList())
                .takeaway(product.isTakeaway())
                .build();
    }
}
