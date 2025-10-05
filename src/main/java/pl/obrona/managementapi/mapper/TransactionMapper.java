package pl.obrona.managementapi.mapper;

import lombok.experimental.UtilityClass;
import pl.obrona.managementapi.model.Product;
import pl.obrona.managementapi.model.Transaction;
import pl.obrona.managementapi.model.command.CreateTransactionCommand;
import pl.obrona.managementapi.model.dto.TransactionDto;

@UtilityClass
public class TransactionMapper {

    public static Transaction mapFromCommand(CreateTransactionCommand command) {
        return Transaction.builder()
                .dateTime(command.getDateTime())
                .paymentMethod(command.getPaymentMethod())
                .totalAmount(command.getTotalAmount())
                .build();
    }

    public static TransactionDto mapToDto(Transaction transaction) {
        return TransactionDto.builder()
                .id(transaction.getId())
                .dateTime(transaction.getDateTime())
                .paymentMethod(transaction.getPaymentMethod())
                .totalAmount(transaction.getTotalAmount())
                .productIds(transaction.getProducts().stream()
                        .map(Product::getId)
                        .toList())
                .employeeId(transaction.getEmployee().getId())
                .build();
    }
}
