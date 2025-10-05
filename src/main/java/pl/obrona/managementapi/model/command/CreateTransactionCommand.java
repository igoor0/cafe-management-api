package pl.obrona.managementapi.model.command;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import pl.obrona.managementapi.model.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class CreateTransactionCommand {
    private Long employeeId;
    private BigDecimal totalAmount;
    private PaymentMethod paymentMethod;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dateTime;

}
