package pl.obrona.managementapi.ingredient.model.command;

import lombok.Builder;
import lombok.Data;
import pl.obrona.managementapi.ingredient.model.Unit;

import java.math.BigDecimal;

@Data
@Builder
public class CreateIngredientCommand {
    private String name;
    private Unit unit;
    private BigDecimal stockQuantity;
    private BigDecimal alertQuantity;
    private BigDecimal unitCost;
}
