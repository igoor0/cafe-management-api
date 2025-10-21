package pl.obrona.managementapi.employee.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class EmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private BigDecimal salaryPerHour;
    private BigDecimal hoursWorked;
    private BigDecimal actualSalary;
}
