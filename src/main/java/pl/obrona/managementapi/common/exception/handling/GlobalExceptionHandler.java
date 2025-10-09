package pl.obrona.managementapi.common.exception.handling;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.obrona.managementapi.common.exception.ApiException;
import pl.obrona.managementapi.common.exception.NotFoundException;

import java.time.Clock;
import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final Clock clock;

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto handleNotFoundException(NotFoundException exception) {
        return new ExceptionDto(exception.getMessage(), LocalDateTime.now(clock));
    }

    @ExceptionHandler(ApiException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleApiException(ApiException exception) {
        return new ExceptionDto(exception.getMessage(), LocalDateTime.now(clock));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return new ExceptionDto(
                exception.getBindingResult().getFieldError().getField(),
                exception.getBindingResult().getAllErrors().get(0).getDefaultMessage(),
                LocalDateTime.now(clock));
    }

}
