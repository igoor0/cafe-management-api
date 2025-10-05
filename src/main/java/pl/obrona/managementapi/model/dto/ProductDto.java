package pl.obrona.managementapi.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Builder
public class ProductDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private Set<Long> productComponentIds;
    private boolean takeaway;
}
