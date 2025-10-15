package pl.obrona.managementapi.statistics.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class StatisticsDto {
    private BigDecimal highestOrderValue;
    private BigDecimal averageOrderValue;
    private BigDecimal cashIncome;
    private BigDecimal cardIncome;
    private BigDecimal totalRevenue;
    private BigDecimal ingredientCosts;
    private BigDecimal fixedCosts;
    private BigDecimal totalExpense;
    private BigDecimal totalProfit;
    private Integer transactionCount;
    private LocalDateTime lastTransactionTime;
}
