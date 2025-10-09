package pl.obrona.managementapi.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.obrona.managementapi.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @EntityGraph(attributePaths = {"productComponents", "productComponents.ingredient"})
    List<Product> findAllByIdIn(List<Long> ids);
}
