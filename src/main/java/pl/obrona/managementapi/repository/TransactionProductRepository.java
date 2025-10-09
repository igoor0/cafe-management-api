package pl.obrona.managementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.obrona.managementapi.model.TransactionProduct;

public interface TransactionProductRepository extends JpaRepository<TransactionProduct, Long> {
}
