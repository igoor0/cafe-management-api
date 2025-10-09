package pl.obrona.managementapi.model.dto;

import lombok.Builder;
import lombok.Getter;
import pl.obrona.managementapi.model.Unit;

import java.math.BigDecimal;

@Getter
@Builder
public class IngredientDto {
    private Long id;
    private String name;
    private Unit unit;
    private BigDecimal stockQuantity;
    private BigDecimal unitCost;
}
