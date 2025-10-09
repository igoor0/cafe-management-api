package pl.obrona.managementapi.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.obrona.managementapi.transaction.model.TransactionProduct;

public interface TransactionProductRepository extends JpaRepository<TransactionProduct, Long> {
}
