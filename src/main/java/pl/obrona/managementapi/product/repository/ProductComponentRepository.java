package pl.obrona.managementapi.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.obrona.managementapi.product.model.ProductComponent;

public interface ProductComponentRepository extends JpaRepository<ProductComponent, Long> {
}
