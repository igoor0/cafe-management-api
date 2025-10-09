package pl.obrona.managementapi.mapper;

import lombok.experimental.UtilityClass;
import pl.obrona.managementapi.model.ProductComponent;
import pl.obrona.managementapi.model.dto.ProductComponentDto;

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
