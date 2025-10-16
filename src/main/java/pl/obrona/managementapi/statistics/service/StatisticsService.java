package pl.obrona.managementapi.statistics.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.obrona.managementapi.common.exception.ApiException;
import pl.obrona.managementapi.common.exception.NotFoundException;
import pl.obrona.managementapi.cost.repository.FixedCostRepository;
import pl.obrona.managementapi.ingredient.mapper.IngredientMapper;
import pl.obrona.managementapi.ingredient.model.dto.IngredientDto;
import pl.obrona.managementapi.ingredient.repository.IngredientRepository;
import pl.obrona.managementapi.statistics.StatisticsRange;
import pl.obrona.managementapi.statistics.dto.ExpandedStatisticsDto;
import pl.obrona.managementapi.statistics.dto.ReportStatisticsDto;
import pl.obrona.managementapi.statistics.dto.StatisticsDto;
import pl.obrona.managementapi.transaction.model.PaymentMethod;
import pl.obrona.managementapi.transaction.repository.TransactionRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static pl.obrona.managementapi.statistics.mapper.StatisticsMapper.toExpandedStatisticsDto;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    private final TransactionRepository transactionRepository;
    private final IngredientRepository ingredientRepository;
    private final FixedCostRepository fixedCostRepository;

    public StatisticsDto get(StatisticsRange statisticsRange) {
        LocalDateTime to = LocalDateTime.now();
        LocalDateTime from = getFromRange(statisticsRange);

        BigDecimal totalRevenue = transactionRepository.sumTotalRevenue(from, to);

        BigDecimal ingredientCosts = transactionRepository.sumIngredientsCost(from, to);
        BigDecimal fixedCosts = fixedCostRepository.sumFixedCosts();
        BigDecimal totalExpense = ingredientCosts.add(fixedCosts);
        BigDecimal totalProfit = totalRevenue.subtract(totalExpense);

        return StatisticsDto.builder()
                .lastTransactionTime(transactionRepository.findLastTransactionDateTime()
                        .orElse(null))
                .averageOrderValue(transactionRepository.averageOrderValue(from, to))
                .cardIncome(transactionRepository.sumByPaymentMethodAndDateRange(PaymentMethod.CARD, from, to))
                .cashIncome(transactionRepository.sumByPaymentMethodAndDateRange(PaymentMethod.CASH, from, to))
                .highestOrderValue(transactionRepository.averageOrderValue(from, to))
                .totalExpense(totalExpense)
                .ingredientCosts(ingredientCosts)
                .fixedCosts(fixedCosts)
                .totalProfit(totalProfit)
                .totalRevenue(totalRevenue)
                .transactionCount(transactionRepository.countItems(from, to))
                .build();
    }

    public ExpandedStatisticsDto getExpanded(StatisticsRange statisticsRange) {
        LocalDateTime to = LocalDateTime.now();
        LocalDateTime from = getFromRange(statisticsRange);
        ExpandedStatisticsDto expandedStatisticsDto = toExpandedStatisticsDto(get(statisticsRange));

        List<Object[]> revenueList = transactionRepository.revenuePerProduct(from, to);
        Map<String, BigDecimal> revenueMap = revenueList.stream()
                .collect(Collectors.toMap(
                        row -> (String) row[0],
                        row -> (BigDecimal) row[1]
                ));
        expandedStatisticsDto.setRevenuePerProduct(revenueMap);

        List<Object[]> unitsList = transactionRepository.getUnitsSoldPerProduct(from, to);
        Map<String, Integer> unitsMap = unitsList.stream()
                .collect(Collectors.toMap(
                        row -> (String) row[0],
                        row -> ((Number) row[1]).intValue()
                ));
        expandedStatisticsDto.setUnitsSoldPerProduct(unitsMap);

        List<Object[]> list = transactionRepository.findTransactionAmountAndCost(from, to);

        BigDecimal averageMargin = list.stream()
                .map(row -> ((BigDecimal) row[0]).subtract((BigDecimal) row[1]))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(new BigDecimal(list.size()), 2, RoundingMode.HALF_UP);

        expandedStatisticsDto.setAverageMarginPerTransaction(averageMargin);
        expandedStatisticsDto.setHighestOrderValue(transactionRepository.getHighestOrderValue(from, to));
        expandedStatisticsDto.setAverageItemsPerTransaction(transactionRepository.getAverageItemsPerTransaction(from, to));
        return expandedStatisticsDto;
    }

    public ReportStatisticsDto getReportStatistics(LocalDate fromDate, LocalDate toDate) {
        LocalDateTime to = toDate.atTime(LocalTime.MAX);
        LocalDateTime from = fromDate.atStartOfDay();
        BigDecimal totalRevenue = transactionRepository.sumTotalRevenue(from, to);

        BigDecimal ingredientCosts = transactionRepository.sumIngredientsCost(from, to);
        BigDecimal fixedCosts = fixedCostRepository.sumFixedCosts();
        BigDecimal totalExpense = ingredientCosts.add(fixedCosts);
        BigDecimal totalProfit = totalRevenue.subtract(totalExpense);
        List<Object[]> revenueList = transactionRepository.revenuePerProduct(from, to);

        Map<String, BigDecimal> revenueMap = revenueList.stream()
                .collect(Collectors.toMap(
                        row -> (String) row[0],
                        row -> (BigDecimal) row[1]
                ));
        List<Object[]> unitsList = transactionRepository.getUnitsSoldPerProduct(from, to);

        Map<String, Integer> unitsMap = unitsList.stream()
                .collect(Collectors.toMap(
                        row -> (String) row[0],
                        row -> ((Number) row[1]).intValue()
                ));

        List<Object[]> list = transactionRepository.findTransactionAmountAndCost(from, to);

        BigDecimal averageMargin = list.stream()
                .map(row -> ((BigDecimal) row[0]).subtract((BigDecimal) row[1]))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(new BigDecimal(list.size()), 2, RoundingMode.HALF_UP);

        return ReportStatisticsDto.builder()
                .lastTransactionTime(transactionRepository.findLastTransactionDateTime()
                        .orElse(null))
                .averageOrderValue(transactionRepository.averageOrderValue(from, to))
                .cardIncome(transactionRepository.sumByPaymentMethodAndDateRange(PaymentMethod.CARD, from, to))
                .cashIncome(transactionRepository.sumByPaymentMethodAndDateRange(PaymentMethod.CASH, from, to))
                .totalExpense(totalExpense)
                .ingredientCosts(ingredientCosts)
                .fixedCosts(fixedCosts)
                .totalProfit(totalProfit)
                .totalRevenue(totalRevenue)
                .transactionCount(transactionRepository.countItems(from, to))
                .highestOrderValue(transactionRepository.getHighestOrderValue(from, to))
                .averageItemsPerTransaction(transactionRepository.getAverageItemsPerTransaction(from, to))
                .averageMarginPerTransaction(averageMargin)
                .unitsSoldPerProduct(unitsMap)
                .revenuePerProduct(revenueMap)
                .build();
    }

    private LocalDateTime getFromRange(StatisticsRange statisticsRange) {
        LocalDateTime from;
        switch (statisticsRange) {
            case DAILY -> from = LocalDate.now().atStartOfDay();
            case WEEKLY -> from = LocalDate.now().atStartOfDay().minusDays(7);
            case MONTHLY -> from = LocalDate.now().atStartOfDay().minusMonths(1);
            case YEARLY -> from = LocalDate.now().atStartOfDay().minusYears(1);
            case OVERALL -> from = transactionRepository.findFirstTransactionDateTime()
                    .orElseThrow(() -> new NotFoundException("Transactions not found"));
            default -> throw new ApiException("Unexpected value: " + statisticsRange);
        }
        return from;
    }

    public List<IngredientDto> getLowStock() {
        return ingredientRepository.findLowStock().stream()
                .map(IngredientMapper::mapToDto)
                .toList();
    }
}
