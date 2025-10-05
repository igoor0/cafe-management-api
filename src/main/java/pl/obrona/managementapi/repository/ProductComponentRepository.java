package pl.obrona.managementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.obrona.managementapi.model.ProductComponent;

public interface ProductComponentRepository extends JpaRepository<ProductComponent, Long> {
}
