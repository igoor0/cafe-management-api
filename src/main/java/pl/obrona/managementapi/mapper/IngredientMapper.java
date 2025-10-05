package pl.obrona.managementapi.mapper;

import lombok.experimental.UtilityClass;
import pl.obrona.managementapi.model.Ingredient;
import pl.obrona.managementapi.model.ProductComponent;
import pl.obrona.managementapi.model.command.CreateIngredientCommand;
import pl.obrona.managementapi.model.dto.IngredientDto;

@UtilityClass
public class IngredientMapper {

    public static Ingredient mapFromCommand(CreateIngredientCommand command) {
        return Ingredient.builder()
                .name(command.getName())
                .unit(command.getUnit())
                .stockQuantity(command.getStockQuantity())
                .unitCost(command.getUnitCost())
                .build();
    }

    public static IngredientDto mapToDto(Ingredient ingredient) {
        return IngredientDto.builder()
                .id(ingredient.getId())
                .name(ingredient.getName())
                .unit(ingredient.getUnit())
                .productComponentIds(ingredient.getProductComponents().stream()
                        .map(ProductComponent::getId)
                        .toList())
                .stockQuantity(ingredient.getStockQuantity())
                .unitCost(ingredient.getUnitCost())
                .build();
    }
}
