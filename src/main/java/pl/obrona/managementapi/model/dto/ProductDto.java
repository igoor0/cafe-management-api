package pl.obrona.managementapi.model.dto;

import lombok.Builder;
import lombok.Getter;
import pl.obrona.managementapi.model.ProductCategory;

import java.math.BigDecimal;

@Getter
@Builder
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private ProductCategory category;
    private BigDecimal price;
    private BigDecimal quantity;
    private BigDecimal unitPrice;
}
