package pl.obrona.managementapi.model.dto;

import lombok.Builder;
import lombok.Getter;
import pl.obrona.managementapi.model.ReportType;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReportDto {
    private ReportType reportType;
    private LocalDateTime reportDate;
}
