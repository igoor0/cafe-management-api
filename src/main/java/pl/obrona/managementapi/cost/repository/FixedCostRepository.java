package pl.obrona.managementapi.cost.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.obrona.managementapi.cost.model.FixedCost;

import java.math.BigDecimal;

public interface FixedCostRepository extends JpaRepository<FixedCost, Long> {

    @Query("""
    SELECT COALESCE(SUM(f.cost), 0)
    FROM FixedCost f
    """)
    BigDecimal sumFixedCosts();
}
