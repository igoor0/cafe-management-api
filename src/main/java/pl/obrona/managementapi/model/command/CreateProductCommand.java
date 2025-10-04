package pl.obrona.managementapi.model.command;

import lombok.Builder;
import lombok.Data;
import pl.obrona.managementapi.model.ProductCategory;

import java.math.BigDecimal;

@Data
@Builder
public class CreateProductCommand {
    private String name;
    private String description;
    private ProductCategory category;
    private BigDecimal price;
    private BigDecimal quantity;
    private BigDecimal unitPrice;
}
