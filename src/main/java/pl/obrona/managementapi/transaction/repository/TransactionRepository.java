package pl.obrona.managementapi.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.obrona.managementapi.transaction.model.PaymentMethod;
import pl.obrona.managementapi.transaction.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t.dateTime FROM Transaction t ORDER BY t.dateTime DESC LIMIT 1")
    Optional<LocalDateTime> findLastTransactionDateTime();

    @Query("Select t.dateTime FROM Transaction t ORDER BY t.dateTime LIMIT 1")
    Optional<LocalDateTime> findFirstTransactionDateTime();


    @Query("""
            SELECT COALESCE(SUM(t.totalAmount), 0)
            FROM Transaction t
            WHERE t.paymentMethod = :method
            AND t.dateTime BETWEEN :start AND :end
            """)
    BigDecimal sumByPaymentMethodAndDateRange(
            @Param("method") PaymentMethod method,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    @Query("""
            SELECT COALESCE(SUM(t.totalAmount), 0)
            FROM Transaction t
            WHERE t.dateTime BETWEEN :start AND :end
            """)
    BigDecimal sumTotalRevenue(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    @Query("""
            SELECT COALESCE(SUM(tp.ingredientsCost), 0)
            FROM Transaction t
            JOIN t.transactionProducts tp
            WHERE t.dateTime BETWEEN :start AND :end
            """)
    BigDecimal sumIngredientsCost(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    @Query("""
            SELECT COALESCE(AVG(t.totalAmount), 0)
            FROM Transaction t
            WHERE t.dateTime BETWEEN :start AND :end
            """)
    BigDecimal averageOrderValue(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    @Query("""
            SELECT COUNT(t)
            FROM Transaction t
            WHERE t.dateTime BETWEEN :start AND :end
            """)
    Integer countItems(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    @Query("""
    SELECT t.totalAmount, COALESCE(SUM(tp.ingredientsCost * tp.quantity), 0)
    FROM Transaction t
    LEFT JOIN t.transactionProducts tp
    WHERE t.dateTime BETWEEN :start AND :end
    GROUP BY t.id
""")
    List<Object[]> findTransactionAmountAndCost(@Param("start") LocalDateTime start,
                                                @Param("end") LocalDateTime end);
    // ✅ Średnia liczba produktów na transakcję
    @Query("""
            SELECT COALESCE(AVG(tpCount), 0)
            FROM (
                SELECT COUNT(tp) as tpCount
                FROM Transaction t
                JOIN t.transactionProducts tp
                WHERE t.dateTime BETWEEN :start AND :end
                GROUP BY t.id
            )
            """)
    BigDecimal getAverageItemsPerTransaction(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    // Revenue per product
    @Query("""
        SELECT tp.product.name, SUM(tp.price * tp.quantity)
        FROM Transaction t
        JOIN t.transactionProducts tp
        WHERE t.dateTime BETWEEN :start AND :end
        GROUP BY tp.product.name
        """)
    List<Object[]> revenuePerProduct(@Param("start") LocalDateTime start,
                                     @Param("end") LocalDateTime end);

    // Units sold per product
    @Query("""
        SELECT tp.product.name, SUM(tp.quantity)
        FROM Transaction t
        JOIN t.transactionProducts tp
        WHERE t.dateTime BETWEEN :start AND :end
        GROUP BY tp.product.name
        """)
    List<Object[]> getUnitsSoldPerProduct(@Param("start") LocalDateTime start,
                                          @Param("end") LocalDateTime end);


    @Query("""
            SELECT max(t.totalAmount)
            FROM Transaction t
            WHERE t.dateTime BETWEEN :start AND :end
            """)
    BigDecimal getHighestOrderValue(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );
}
