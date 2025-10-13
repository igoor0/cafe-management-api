package pl.obrona.managementapi.statistics;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.obrona.managementapi.common.exception.ApiException;
import pl.obrona.managementapi.transaction.model.PaymentMethod;
import pl.obrona.managementapi.transaction.model.Transaction;
import pl.obrona.managementapi.transaction.model.TransactionProduct;
import pl.obrona.managementapi.transaction.repository.TransactionRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    private final TransactionRepository transactionRepository;

    private static final BigDecimal ZERO = BigDecimal.ZERO.setScale(5, RoundingMode.HALF_UP);

    public StatisticsDto getAll(@Nullable Range range) {
        LocalDateTime to = LocalDateTime.now();
        LocalDateTime from;

        if (range == null) {
            from = LocalDate.of(2025, 1, 1).atStartOfDay();
        } else {
            switch (range) {
                case DAILY -> from = LocalDate.now().atStartOfDay();
                case WEEKLY -> from = LocalDate.now().atStartOfDay().minusDays(7);
                case MONTHLY -> from = LocalDate.now().atStartOfDay().minusMonths(1);
                case YEARLY -> from = LocalDate.now().atStartOfDay().minusYears(1);
                default -> throw new ApiException("Invalid range " + range);
            }
        }

        List<Transaction> transactions = transactionRepository.findAllByDateTimeAfterAndDateTimeBefore(from, to);

        return StatisticsDto.builder()
                .lastTransactionTime(transactionRepository.findLastTransactionDateTime()
                        .map(Transaction::getDateTime)
                        .orElse(null))
                .averageOrderValue(calculateAverageOrderValue(transactions))
                .cardIncome(calculateCardIncome(transactions))
                .cashIncome(calculateCashIncome(transactions))
                .totalExpense(calculateTotalExpense(transactions))
                .totalProfit(calculateTotalProfit(transactions))
                .totalRevenue(calculateTotalRevenue(transactions))
                .transactionCount(transactions.size())
                .build();
    }

    private BigDecimal calculateAverageOrderValue(List<Transaction> transactions) {
        if (transactions.isEmpty()) return BigDecimal.ZERO;
        return calculateTotalRevenue(transactions)
                .divide(BigDecimal.valueOf(transactions.size()), 2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateCashIncome(List<Transaction> transactions) {
        return transactions.stream()
                .filter(tx -> tx.getPaymentMethod() == PaymentMethod.CASH)
                .map(this::safeSumTransactionProducts)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateCardIncome(List<Transaction> transactions) {
        return transactions.stream()
                .filter(tx -> tx.getPaymentMethod() == PaymentMethod.CARD)
                .map(this::safeSumTransactionProducts)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateTotalRevenue(List<Transaction> transactions) {
        return transactions.stream()
                .map(t -> t.getTotalAmount() != null ? t.getTotalAmount() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateTotalExpense(List<Transaction> transactions) {
        return transactions.stream()
                .map(t -> t.getTransactionProducts().stream()
                        .map(TransactionProduct::getIngredientsCost)
                        .reduce(ZERO, BigDecimal::add))
                .reduce(ZERO, BigDecimal::add);
    }

    private BigDecimal calculateTotalProfit(List<Transaction> transactions) {
        return calculateTotalRevenue(transactions).subtract(calculateTotalExpense(transactions));
    }

    private BigDecimal safeSumTransactionProducts(Transaction t) {
        if (t.getTransactionProducts() == null) return BigDecimal.ZERO;
        return t.getTransactionProducts().stream()
                .map(tp -> tp.getPrice() != null ? tp.getPrice() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
