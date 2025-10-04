package pl.obrona.managementapi.mapper;

import lombok.experimental.UtilityClass;
import pl.obrona.managementapi.model.Product;
import pl.obrona.managementapi.model.command.CreateProductCommand;
import pl.obrona.managementapi.model.dto.ProductDto;

@UtilityClass
public class ProductMapper {

    public static Product mapFromCommand(CreateProductCommand command) {
        return Product.builder()
                .name(command.getName())
                .description(command.getDescription())
                .category(command.getCategory())
                .price(command.getPrice())
                .quantity(command.getQuantity())
                .unitPrice(command.getUnitPrice())
                .build();
    }

    public static ProductDto mapToDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .category(product.getCategory())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .unitPrice(product.getUnitPrice())
                .build();
    }
}
