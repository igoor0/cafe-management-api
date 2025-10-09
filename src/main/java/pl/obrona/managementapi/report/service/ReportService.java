package pl.obrona.managementapi.report.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.obrona.managementapi.report.model.dto.ReportDto;
import pl.obrona.managementapi.report.repository.ReportRepository;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;

    public ReportDto getAll() {
        return null;
    }
}
