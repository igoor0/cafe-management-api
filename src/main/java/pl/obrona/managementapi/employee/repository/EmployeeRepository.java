package pl.obrona.managementapi.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.obrona.managementapi.employee.model.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("""
            select e.id
            from Employee e
            """)
    List<Long> findAllIds();
}
