package pl.obrona.managementapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Product {
    @Id
    private Long id;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private ProductCategory category;
    private BigDecimal price;
    private BigDecimal quantity;
    private BigDecimal unitPrice;

}
