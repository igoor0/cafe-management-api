package pl.obrona.managementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.obrona.managementapi.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
