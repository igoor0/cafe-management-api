package pl.obrona.managementapi.employee.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.obrona.managementapi.common.exception.NotFoundException;
import pl.obrona.managementapi.employee.mapper.EmployeeMapper;
import pl.obrona.managementapi.employee.model.Employee;
import pl.obrona.managementapi.employee.model.command.CreateEmployeeCommand;
import pl.obrona.managementapi.employee.model.dto.EmployeeDto;
import pl.obrona.managementapi.employee.repository.EmployeeRepository;

import java.util.List;

import static pl.obrona.managementapi.employee.mapper.EmployeeMapper.mapFromCommand;
import static pl.obrona.managementapi.employee.mapper.EmployeeMapper.mapToDto;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeDto create(CreateEmployeeCommand command) {
        Employee employee = mapFromCommand(command);
        return mapToDto(employeeRepository.save(employee));
    }

    public List<EmployeeDto> getAll() {
        return employeeRepository.findAll().stream()
                .map(EmployeeMapper::mapToDto)
                .toList();
    }

    public EmployeeDto getById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found with i"));
        return mapToDto(employee);
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }
}
