package pl.obrona.managementapi.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.obrona.managementapi.transaction.model.Transaction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t ORDER BY t.dateTime DESC LIMIT 1")
    Optional<Transaction> findLastTransactionDateTime();

    List<Transaction> findAllByDateTimeAfterAndDateTimeBefore(LocalDateTime from, LocalDateTime to);
}
