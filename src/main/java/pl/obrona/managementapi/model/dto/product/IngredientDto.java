package pl.obrona.managementapi.model.dto.product;

import lombok.Builder;
import lombok.Data;
import pl.obrona.managementapi.model.product.Unit;

import java.math.BigDecimal;

@Data
@Builder
public class IngredientDto {
    private Long id;
    private String name;
    private Unit unit;
    private BigDecimal stockQuantity;
    private BigDecimal unitCost;
}
