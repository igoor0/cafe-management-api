package pl.obrona.managementapi.product.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.obrona.managementapi.product.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @EntityGraph(attributePaths = {"productComponents", "productComponents.ingredient"})
    List<Product> findAllByIdIn(List<Long> ids);
}
