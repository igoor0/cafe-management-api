package pl.obrona.managementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.obrona.managementapi.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
