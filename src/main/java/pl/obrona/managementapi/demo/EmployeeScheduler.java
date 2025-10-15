package pl.obrona.managementapi.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.obrona.managementapi.employee.model.Employee;
import pl.obrona.managementapi.employee.repository.EmployeeRepository;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EmployeeScheduler {

    private final EmployeeRepository employeeRepository;

    // Logowanie o 8:15
    @Scheduled(cron = "0 15 8 * * ?")
    public void loginEmployees() {
        List<Employee> employees = employeeRepository.findAllByAtWorkFalse();
        int countToLogin = (int) Math.ceil(employees.size() * 0.6);

        Collections.shuffle(employees);
        for (int i = 0; i < countToLogin; i++) {
            Employee employee = employees.get(i);
            employee.setAtWork(true);
            employee.setLastLoggedAt(LocalDateTime.now());
            employeeRepository.save(employee);
            System.out.println("[Scheduler] " + employee.getFirstName() + " " + employee.getLastName() + " zalogowany o " + employee.getLastLoggedAt());
        }
    }

    // Wylogowanie o 21:30
    @Scheduled(cron = "0 30 21 * * ?")
    public void logoutEmployees() {
        List<Employee> employees = employeeRepository.findAllByAtWorkTrue();

        Collections.shuffle(employees);
        for (Employee employee : employees) {
            employee.setAtWork(false);

            if (employee.getLastLoggedAt() != null) {
                double hoursWorked = Duration.between(employee.getLastLoggedAt(), LocalDateTime.now()).toMinutes() / 60.0;
                employee.setHoursWorked(employee.getHoursWorked().add(BigDecimal.valueOf(hoursWorked)));
            }

            employeeRepository.save(employee);
            System.out.println("[Scheduler] " + employee.getFirstName() + " " + employee.getLastName() + " wylogowany. Suma godzin: " + employee.getHoursWorked());
        }
    }
}
