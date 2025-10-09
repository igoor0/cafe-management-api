package pl.obrona.managementapi.exception.handling;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ExceptionDto {
    private String field;
    private final String message;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private final LocalDateTime timestamp;

    public ExceptionDto(String field, String message, LocalDateTime timestamp) {
        this.field = field;
        this.message = message;
        this.timestamp = timestamp;
    }
}
