package pl.obrona.managementapi.ingredient.mapper;

import lombok.experimental.UtilityClass;
import pl.obrona.managementapi.ingredient.model.Ingredient;
import pl.obrona.managementapi.ingredient.model.command.CreateIngredientCommand;
import pl.obrona.managementapi.ingredient.model.dto.IngredientDto;

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
