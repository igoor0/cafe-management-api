package pl.obrona.managementapi.mapper;

import lombok.experimental.UtilityClass;
import pl.obrona.managementapi.model.command.CreateIngredientCommand;
import pl.obrona.managementapi.model.dto.product.IngredientDto;
import pl.obrona.managementapi.model.product.Ingredient;

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
                .stockQuantity(ingredient.getStockQuantity())
                .unitCost(ingredient.getUnitCost())
                .build();
    }
}
