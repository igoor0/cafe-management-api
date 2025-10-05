package pl.obrona.managementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.obrona.managementapi.model.product.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
