package pl.obrona.managementapi.model.command;

import lombok.Builder;
import lombok.Data;
import pl.obrona.managementapi.model.PaymentMethod;

import java.util.List;

@Data
@Builder
public class CreateTransactionCommand {
    private Long employeeId;
    private PaymentMethod paymentMethod;
    private List<Long> productIds;

}
