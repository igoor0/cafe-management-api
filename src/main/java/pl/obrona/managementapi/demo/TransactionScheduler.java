package pl.obrona.managementapi.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.obrona.managementapi.employee.model.Employee;
import pl.obrona.managementapi.employee.repository.EmployeeRepository;
import pl.obrona.managementapi.product.model.Product;
import pl.obrona.managementapi.product.repository.ProductRepository;
import pl.obrona.managementapi.transaction.model.PaymentMethod;
import pl.obrona.managementapi.transaction.model.command.CreateTransactionCommand;
import pl.obrona.managementapi.transaction.model.dto.TransactionDto;
import pl.obrona.managementapi.transaction.service.TransactionService;

import java.util.Collections;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
@Profile("demo")
@RequiredArgsConstructor
public class TransactionScheduler {

    private final EmployeeRepository employeeRepository;
    private final ProductRepository productRepository;
    private final TransactionService transactionService;

    @Scheduled(fixedRate = 30000)
    public void generateRandomTransaction() {
        Random random = new Random();

        List<Employee> employees = employeeRepository.findAllByAtWorkTrue();
        List<Product> products = productRepository.findAll();

        if (employees.isEmpty() || products.isEmpty()) {
            log.warn("[Scheduler] Brak dostępnych pracowników lub produktów do wygenerowania transakcji");
            return;
        }

        // Losowy pracownik
        Employee employee = employees.get(random.nextInt(employees.size()));

        // Losowy wybór produktów z losową ilością każdej pozycji
        List<Long> productIds = products.stream()
                .flatMap(product -> {
                    int quantity = random.nextInt(3) + 1; // 1-3 sztuki każdego produktu
                    return Collections.nCopies(quantity, product.getId()).stream();
                })
                .toList();

        CreateTransactionCommand command = CreateTransactionCommand.builder()
                .employeeId(employee.getId())
                .paymentMethod(random.nextBoolean() ? PaymentMethod.CASH : PaymentMethod.CARD)
                .productIds(productIds)
                .build();

        try {
            TransactionDto transaction = transactionService.create(command);
            // Log po utworzeniu transakcji
            log.info("[Scheduler] Utworzono transakcję: id={}, employeeId={}, totalAmount={}, paymentMethod={}, productIds with their quantities={}",
                    transaction.getId(),
                    transaction.getEmployeeId(),
                    transaction.getTotalAmount(),
                    transaction.getPaymentMethod(),
                    transaction.getProductIdQuantities()
            );
        } catch (Exception e) {
            // Log w przypadku błędu (np. brak składników)
            log.warn("[Scheduler] Nie udało się utworzyć transakcji dla employeeId={} z produktami {}: {}",
                    employee.getId(),
                    productIds,
                    e.getMessage()
            );
        }
    }
}
