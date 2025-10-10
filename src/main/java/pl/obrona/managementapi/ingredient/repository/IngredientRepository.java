package pl.obrona.managementapi.ingredient.repository;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.obrona.managementapi.ingredient.model.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Ingredient> findWithLockingById(Long id);

    @Query("""
            select i
            from Ingredient i
            where ((:lowStock is null or :lowStock is false) or i.stockQuantity <= i.alertQuantity)
            """)
    List<Ingredient> findAllByLowStock(@Param("lowStock") Boolean lowStock);
}
