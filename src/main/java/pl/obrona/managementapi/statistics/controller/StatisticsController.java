package pl.obrona.managementapi.statistics.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.obrona.managementapi.ingredient.model.dto.IngredientDto;
import pl.obrona.managementapi.statistics.StatisticsRange;
import pl.obrona.managementapi.statistics.dto.ExpandedStatisticsDto;
import pl.obrona.managementapi.statistics.dto.StatisticsDto;
import pl.obrona.managementapi.statistics.service.StatisticsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/statistics")
public class StatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping
    public StatisticsDto get(@RequestParam(required = false, defaultValue = "OVERALL") StatisticsRange statisticsRange ) {
        return statisticsService.get(statisticsRange);
    }

    @GetMapping("/expanded")
    public ExpandedStatisticsDto getExpanded(@RequestParam(required = false, defaultValue = "OVERALL") StatisticsRange statisticsRange ) {
        return statisticsService.getExpanded(statisticsRange);
    }

    @GetMapping("/low-stock")
    public List<IngredientDto> getLowStock() {
        return statisticsService.getLowStock();
    }

}
