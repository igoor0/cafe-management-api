package pl.obrona.managementapi.report.model.command;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class CreateReportCommand {
    private LocalDate from;
    private LocalDate to;
    private String title;
}
