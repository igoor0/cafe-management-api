package pl.obrona.managementapi.report.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.obrona.managementapi.report.model.dto.ReportDto;
import pl.obrona.managementapi.report.service.ReportService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/reports")
public class ReportController {

    private final ReportService reportService;

    @GetMapping
    public ReportDto getAll() {
        return reportService.getAll();
    }

}
