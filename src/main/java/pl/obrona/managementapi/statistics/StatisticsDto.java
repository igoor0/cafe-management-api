package pl.obrona.managementapi.statistics;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class StatisticsDto {
    private BigDecimal averageOrderValue;
    private BigDecimal cashIncome;
    private BigDecimal cardIncome;
    private BigDecimal totalRevenue;
    private BigDecimal totalExpense;
    private BigDecimal totalProfit;      // (opcjonalnie, jeśli liczysz koszty)
    private Integer transactionCount;    // Ile transakcji
    private LocalDateTime lastTransactionTime;
}
