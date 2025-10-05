package pl.obrona.managementapi.model.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import pl.obrona.managementapi.model.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class TransactionDto {

    private Long id;
    private Long employeeId;
    private BigDecimal totalAmount;
    private List<Long> productIds;
    private PaymentMethod paymentMethod;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dateTime;
}
