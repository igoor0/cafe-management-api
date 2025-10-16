package pl.obrona.managementapi.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.obrona.managementapi.report.model.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {

}
