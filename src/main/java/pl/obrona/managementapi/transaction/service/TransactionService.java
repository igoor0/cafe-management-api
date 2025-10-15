package pl.obrona.managementapi.transaction.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.obrona.managementapi.common.exception.ApiException;
import pl.obrona.managementapi.common.exception.NotFoundException;
import pl.obrona.managementapi.employee.model.Employee;
import pl.obrona.managementapi.employee.repository.EmployeeRepository;
import pl.obrona.managementapi.ingredient.model.Ingredient;
import pl.obrona.managementapi.ingredient.repository.IngredientRepository;
import pl.obrona.managementapi.product.model.Product;
import pl.obrona.managementapi.product.model.ProductComponent;
import pl.obrona.managementapi.product.repository.ProductRepository;
import pl.obrona.managementapi.transaction.mapper.TransactionMapper;
import pl.obrona.managementapi.transaction.model.Transaction;
import pl.obrona.managementapi.transaction.model.TransactionProduct;
import pl.obrona.managementapi.transaction.model.command.CreateTransactionCommand;
import pl.obrona.managementapi.transaction.model.dto.TransactionDto;
import pl.obrona.managementapi.transaction.repository.TransactionRepository;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static pl.obrona.managementapi.transaction.mapper.TransactionMapper.mapToDto;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final EmployeeRepository employeeRepository;
    private final ProductRepository productRepository;
    private final IngredientRepository ingredientRepository;

    @Transactional
    public TransactionDto create(CreateTransactionCommand command) {
        Transaction transaction = new Transaction();

        Employee employee = employeeRepository.findById(command.getEmployeeId())
                .orElseThrow(() -> new NotFoundException("Employee not found with id: " + command.getEmployeeId()));

        transaction.setEmployee(employee);
        transaction.setPaymentMethod(command.getPaymentMethod());

        Map<Long, Long> productCounts = command.getProductIds().stream()
                .collect(Collectors.groupingBy(id -> id, Collectors.counting()));

        Set<TransactionProduct> transactionProducts = new HashSet<>();
        BigDecimal totalAmount = BigDecimal.ZERO;

        List<Product> products = productRepository.findAllByIdIn(productCounts.keySet().stream().toList());

        for (Product product : products) {
            Long quantity = productCounts.get(product.getId());

            TransactionProduct tp = new TransactionProduct();
            tp.setTransaction(transaction);
            tp.setProduct(product);
            tp.setName(product.getName());
            tp.setPrice(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
            tp.setTakeaway(product.isTakeaway());
            tp.setQuantity(quantity.intValue());

            BigDecimal ingredientsCost = BigDecimal.ZERO;
            for (ProductComponent component : product.getProductComponents()) {
                Ingredient ingredient = component.getIngredient();
                BigDecimal usedAmount = component.getAmount().multiply(BigDecimal.valueOf(quantity));

                if (ingredient.getStockQuantity().compareTo(usedAmount) < 0) {
                    throw new ApiException("Not enough stock for ingredient: " + ingredient.getName());
                }

                ingredient.setStockQuantity(ingredient.getStockQuantity().subtract(usedAmount));
                ingredientRepository.save(ingredient);

                ingredientsCost = ingredientsCost.add(ingredient.getUnitCost().multiply(usedAmount));
            }

            tp.setIngredientsCost(ingredientsCost);

            transactionProducts.add(tp);
            totalAmount = totalAmount.add(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
        }

        transaction.setTransactionProducts(transactionProducts);
        transaction.setTotalAmount(totalAmount);

        transactionRepository.save(transaction);

        return mapToDto(transaction);
    }

    public List<TransactionDto> getAll() {
        return transactionRepository.findAll().stream()
                .map(TransactionMapper::mapToDto)
                .toList();
    }

    public TransactionDto getById(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Transaction not found with id: " + id));
        return mapToDto(transaction);
    }
}
