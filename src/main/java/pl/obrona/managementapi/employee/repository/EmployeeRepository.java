package pl.obrona.managementapi.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.obrona.managementapi.employee.model.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAllByAtWorkFalse();

    List<Employee> findAllByAtWorkTrue();
}
