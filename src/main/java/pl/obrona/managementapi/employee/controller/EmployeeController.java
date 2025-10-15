package pl.obrona.managementapi.employee.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.obrona.managementapi.employee.model.command.CreateEmployeeCommand;
import pl.obrona.managementapi.employee.model.dto.EmployeeDto;
import pl.obrona.managementapi.employee.service.EmployeeService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto create(@RequestBody @Valid CreateEmployeeCommand command) {
        return employeeService.create(command);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDto> getAll() {
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDto getById(@PathVariable Long id) {
        return employeeService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteById(id);
    }

    @PostMapping("/generateSalary/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto withdrawSalary(@PathVariable Long id) {
        return employeeService.withdrawSalary(id);
    }

    @PatchMapping("/update-hours/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDto updateHours(@PathVariable Long id, @RequestBody BigDecimal updatedHours) {
        return employeeService.updateHours(id, updatedHours);
    }

}
