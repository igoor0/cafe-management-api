package pl.obrona.managementapi.demo.pos_simulator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.obrona.managementapi.employee.repository.EmployeeRepository;
import pl.obrona.managementapi.product.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DemoService {

    private final EmployeeRepository employeeRepository;
    private final ProductRepository productRepository;

    public List<Long> getEmployeeIds() {
        return employeeRepository.findAllIds();
    }

    public List<Long> getProductIds() {
        return productRepository.findAllIds();
    }
}
