package pl.obrona.managementapi.mapper;

import lombok.experimental.UtilityClass;
import pl.obrona.managementapi.model.ProductComponent;
import pl.obrona.managementapi.model.command.CreateProductComponentCommand;
import pl.obrona.managementapi.model.dto.ProductComponentDto;

@UtilityClass
public class ProductComponentMapper {

    public static ProductComponent mapFromCommand(CreateProductComponentCommand command) {
        return ProductComponent.builder()
                .amount(command.getAmount())
                .build();
    }

    public static ProductComponentDto mapToDto(ProductComponent productComponent) {
        return ProductComponentDto.builder()
                .id(productComponent.getId())
                .ingredientId(productComponent.getIngredient().getId())
                .amount(productComponent.getAmount())
                .build();
    }
}
