package pl.obrona.managementapi.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class ProductComponentDto {
    private Long id;
    private Long productId;
    private Long ingredientId;
    private BigDecimal amount;
}
