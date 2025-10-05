package pl.obrona.managementapi.model.command;

import lombok.Builder;
import lombok.Data;
import pl.obrona.managementapi.model.product.Unit;

import java.math.BigDecimal;

@Data
@Builder
public class CreateIngredientCommand {
    private String name;
    private Unit unit;
    private BigDecimal stockQuantity;
    private BigDecimal unitCost;
}
