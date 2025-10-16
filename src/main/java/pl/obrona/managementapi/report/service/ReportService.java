package pl.obrona.managementapi.report.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.obrona.managementapi.report.model.Report;
import pl.obrona.managementapi.report.model.command.CreateReportCommand;
import pl.obrona.managementapi.report.repository.ReportRepository;
import pl.obrona.managementapi.statistics.dto.ReportStatisticsDto;
import pl.obrona.managementapi.statistics.service.StatisticsService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final StatisticsService statisticsService;
    private final PdfReportService pdfReportService;
    private final ReportRepository reportRepository;

    public byte[] generateAndSaveReport(CreateReportCommand command) {
        LocalDate from = command.getFrom();
        LocalDate to = command.getTo();
        String commandTitle = command.getTitle();
        ReportStatisticsDto stats = statisticsService.getReportStatistics(from, to);

        String title = "Report_" + commandTitle + "_" + from + " - " + to;
        byte[] pdfData = pdfReportService.generatePdf(title, stats);

        Report report = Report.builder()
                .fromDate(from)
                .toDate(to)
                .fileName(title.replace(" ", "_") + ".pdf")
                .fileData(pdfData)
                .generatedAt(LocalDateTime.now())
                .build();

        reportRepository.save(report);

        return pdfData;
    }

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }
}

