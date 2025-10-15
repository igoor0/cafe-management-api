package pl.obrona.managementapi.statistics.mapper;

import pl.obrona.managementapi.statistics.dto.ExpandedStatisticsDto;
import pl.obrona.managementapi.statistics.dto.StatisticsDto;

public class StatisticsMapper {

    public static ExpandedStatisticsDto toExpandedStatisticsDto(StatisticsDto statisticsDto) {
        return ExpandedStatisticsDto.builder()
                .highestOrderValue(statisticsDto.getHighestOrderValue())
                .averageOrderValue(statisticsDto.getAverageOrderValue())
                .cashIncome(statisticsDto.getCashIncome())
                .cardIncome(statisticsDto.getCardIncome())
                .totalRevenue(statisticsDto.getTotalRevenue())
                .ingredientCosts(statisticsDto.getIngredientCosts())
                .fixedCosts(statisticsDto.getFixedCosts())
                .totalExpense(statisticsDto.getTotalExpense())
                .totalProfit(statisticsDto.getTotalProfit())
                .transactionCount(statisticsDto.getTransactionCount())
                .lastTransactionTime(statisticsDto.getLastTransactionTime())
                .build();
    }
}
