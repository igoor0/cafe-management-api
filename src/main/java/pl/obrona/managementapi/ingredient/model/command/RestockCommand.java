package pl.obrona.managementapi.ingredient.model.command;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class RestockCommand {
    @PositiveOrZero
    private BigDecimal quantity;
}
