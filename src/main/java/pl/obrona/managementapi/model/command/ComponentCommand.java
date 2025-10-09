package pl.obrona.managementapi.model.command;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ComponentCommand {
    private Long ingredientId;
    private BigDecimal amount;
}
