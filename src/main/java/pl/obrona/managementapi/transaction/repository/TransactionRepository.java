package pl.obrona.managementapi.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.obrona.managementapi.transaction.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
