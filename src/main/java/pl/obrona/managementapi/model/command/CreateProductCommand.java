package pl.obrona.managementapi.model.command;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Builder
public class CreateProductCommand {
    private String name;
    private BigDecimal price;
    private Set<Long> productComponentIds;
    private boolean takeaway;

    //todo walidacja w commandach
}
