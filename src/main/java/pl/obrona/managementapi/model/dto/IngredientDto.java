package pl.obrona.managementapi.model.dto;

import lombok.Builder;
import lombok.Getter;
import pl.obrona.managementapi.model.Unit;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
public class IngredientDto {
    private Long id;
    private String name;
    private Unit unit;
    private List<Long> productComponentIds;
    private BigDecimal stockQuantity;
    private BigDecimal unitCost;
}
