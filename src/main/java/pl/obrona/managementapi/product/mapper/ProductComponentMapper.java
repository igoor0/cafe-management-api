package pl.obrona.managementapi.product.mapper;

import lombok.experimental.UtilityClass;
import pl.obrona.managementapi.product.model.ProductComponent;
import pl.obrona.managementapi.product.model.dto.ProductComponentDto;

@UtilityClass
public class ProductComponentMapper {

    public static ProductComponentDto mapToDto(ProductComponent productComponent) {
        return ProductComponentDto.builder()
                .id(productComponent.getId())
                .ingredientId(productComponent.getIngredient().getId())
                .amount(productComponent.getAmount())
                .build();
    }
}
