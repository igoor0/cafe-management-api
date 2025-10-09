package pl.obrona.managementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.obrona.managementapi.model.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {

}
