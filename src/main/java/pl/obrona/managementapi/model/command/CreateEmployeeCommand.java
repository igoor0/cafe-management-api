package pl.obrona.managementapi.model.command;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CreateEmployeeCommand {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private BigDecimal salaryPerHour;
}
