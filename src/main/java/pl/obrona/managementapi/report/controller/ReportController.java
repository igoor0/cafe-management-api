package pl.obrona.managementapi.report.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.obrona.managementapi.report.model.Report;
import pl.obrona.managementapi.report.model.command.CreateReportCommand;
import pl.obrona.managementapi.report.service.ReportService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reports")
public class ReportController {

    private final ReportService reportService;

    @PostMapping
    public ResponseEntity<byte[]> createReport(@RequestBody CreateReportCommand command) {
        byte[] pdf = reportService.generateAndSaveReport(command);
        String fileName = "attachment; filename=Report_" + command.getTitle() + "_" + command.getFrom() + "_" + command.getTo() + ".pdf";
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, fileName)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    @GetMapping
    public List<Report> getAllReports() {
        return reportService.getAllReports();
    }

}
