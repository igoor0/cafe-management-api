package pl.obrona.managementapi.mapper;

import lombok.experimental.UtilityClass;
import pl.obrona.managementapi.model.Transaction;
import pl.obrona.managementapi.model.TransactionProduct;
import pl.obrona.managementapi.model.command.CreateTransactionCommand;
import pl.obrona.managementapi.model.dto.TransactionDto;

import java.util.Map;
import java.util.stream.Collectors;

@UtilityClass
public class TransactionMapper {

    public static Transaction mapFromCommand(CreateTransactionCommand command) {
        return Transaction.builder()
                .paymentMethod(command.getPaymentMethod())
                .build();
    }

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
                .employeeId(transaction.getEmployee().getId())
                .build();
    }
}
