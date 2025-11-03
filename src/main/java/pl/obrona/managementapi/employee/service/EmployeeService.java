package pl.obrona.managementapi.employee.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.obrona.managementapi.common.exception.NotFoundException;
import pl.obrona.managementapi.cost.model.CostType;
import pl.obrona.managementapi.cost.model.FixedCost;
import pl.obrona.managementapi.cost.repository.FixedCostRepository;
import pl.obrona.managementapi.employee.mapper.EmployeeMapper;
import pl.obrona.managementapi.employee.model.Employee;
import pl.obrona.managementapi.employee.model.command.CreateEmployeeCommand;
import pl.obrona.managementapi.employee.model.dto.EmployeeDto;
import pl.obrona.managementapi.employee.repository.EmployeeRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static pl.obrona.managementapi.employee.mapper.EmployeeMapper.mapFromCommand;
import static pl.obrona.managementapi.employee.mapper.EmployeeMapper.mapToDto;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final FixedCostRepository fixedCostRepository;

    public EmployeeDto create(CreateEmployeeCommand command) {
        Employee employee = mapFromCommand(command);
        employee.setHoursWorked(BigDecimal.ZERO);
        return mapToDto(employeeRepository.save(employee));
    }

    public List<EmployeeDto> getAll() {
        return employeeRepository.findAll().stream()
                .map(EmployeeMapper::mapToDto)
                .toList();
    }

    public EmployeeDto getById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id));
        return mapToDto(employee);
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }


    @Transactional
    public EmployeeDto withdrawSalary(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NotFoundException("Employee not found with id: " + employeeId));

        BigDecimal hoursWorked = employee.getHoursWorked();
        BigDecimal hourlyRate = employee.getSalaryPerHour();

        BigDecimal salary = hoursWorked.multiply(hourlyRate);

        fixedCostRepository.save(FixedCost.builder()
                .description("Paycheck for " + employee.getFirstName() + " " + employee.getLastName())
                .cost(salary)
                .costType(CostType.PAYCHECK)
                .createdAt(LocalDateTime.now())
                .build()
        );

        employee.setHoursWorked(BigDecimal.ZERO);
        return mapToDto(employeeRepository.save(employee));
    }

    public EmployeeDto updateHours(Long id, BigDecimal updatedHours) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id));
        employee.setHoursWorked(updatedHours);
        return mapToDto(employeeRepository.save(employee));
    }
}
