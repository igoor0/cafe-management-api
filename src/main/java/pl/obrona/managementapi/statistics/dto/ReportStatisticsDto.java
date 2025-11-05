package pl.obrona.managementapi.statistics.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Builder
public class ReportStatisticsDto {
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
    private LocalDateTime firstTransactionTime;
    private LocalDateTime lastTransactionTime;
    private BigDecimal averageMarginPerTransaction;
    private BigDecimal averageItemsPerTransaction;
    private Map<String, BigDecimal> revenuePerProduct;
    private Map<String, Integer> unitsSoldPerProduct;
}
