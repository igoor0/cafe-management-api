package pl.obrona.managementapi.model.command;

import lombok.Builder;
import lombok.Data;
import pl.obrona.managementapi.model.Unit;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class CreateIngredientCommand {
    private String name;
    private Unit unit;
    private List<Long> productComponentIds;
    private BigDecimal stockQuantity;
    private BigDecimal unitCost;
}
