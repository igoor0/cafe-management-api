package pl.obrona.managementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.obrona.managementapi.model.product.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
