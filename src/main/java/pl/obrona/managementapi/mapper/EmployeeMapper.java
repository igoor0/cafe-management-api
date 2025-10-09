package pl.obrona.managementapi.mapper;

import lombok.experimental.UtilityClass;
import pl.obrona.managementapi.model.Employee;
import pl.obrona.managementapi.model.command.CreateEmployeeCommand;
import pl.obrona.managementapi.model.dto.EmployeeDto;

@UtilityClass
public class EmployeeMapper {

    public static Employee mapFromCommand(CreateEmployeeCommand command) {
        return Employee.builder()
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .email(command.getEmail())
                .phoneNumber(command.getPhoneNumber())
                .salaryPerHour(command.getSalaryPerHour())
                .build();
    }

    public static EmployeeDto mapToDto(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .phoneNumber(employee.getPhoneNumber())
                .salaryPerHour(employee.getSalaryPerHour())
                .hoursWorked(employee.getHoursWorked())
                .build();
    }
}
