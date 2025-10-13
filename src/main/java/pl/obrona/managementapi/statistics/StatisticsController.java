package pl.obrona.managementapi.statistics;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/statistics")
public class StatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping
    public StatisticsDto getAll(@RequestParam(required = false) Range range) {
        return statisticsService.getAll(range);
    }

}
