package pl.obrona.managementapi.transaction.mapper;

import lombok.experimental.UtilityClass;
import pl.obrona.managementapi.transaction.model.Transaction;
import pl.obrona.managementapi.transaction.model.TransactionProduct;
import pl.obrona.managementapi.transaction.model.dto.TransactionDto;

import java.util.Map;
import java.util.stream.Collectors;

@UtilityClass
public class TransactionMapper {

    public static TransactionDto mapToDto(Transaction transaction) {
        Map<Long, Integer> productIdQuantities = transaction.getTransactionProducts().stream()
                .collect(Collectors.toMap(
                        tp -> tp.getProduct().getId(),
                        TransactionProduct::getQuantity
                ));

        return TransactionDto.builder()
                .id(transaction.getId())
                .dateTime(transaction.getDateTime())
                .paymentMethod(transaction.getPaymentMethod())
                .totalAmount(transaction.getTotalAmount())
                .productIdQuantities(productIdQuantities)
                .employeeId(transaction.getEmployee() != null ? transaction.getEmployee().getId() : 0)
                .build();
    }
}
