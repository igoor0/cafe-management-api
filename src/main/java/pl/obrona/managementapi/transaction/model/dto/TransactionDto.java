package pl.obrona.managementapi.transaction.model.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import pl.obrona.managementapi.transaction.model.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Builder
public class TransactionDto {

    private Long id;
    private Long employeeId;
    private BigDecimal totalAmount;
    private Map<Long, Integer> productIdQuantities;
    private PaymentMethod paymentMethod;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dateTime;
}
