package pl.obrona.managementapi.cost.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CreateFixedCostCommand {
    private String description;
    private BigDecimal cost;
}
