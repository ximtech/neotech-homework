package neotech.homework.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandlingAdvice {

    @ExceptionHandler({ApiException.class})  // custom exception handler
    public Mono<ResponseEntity<Map<String, String>>> handleCustomExceptions(ApiException e) {
        return Mono.just(ResponseEntity.status(e.getStatus()))
                .flatMap(status ->
                        Mono.deferContextual(Mono::just).map(context -> {
                            log.error(e.getMessage());
                            return status.body(Map.of("error", e.getMessage()));
                        })).publishOn(Schedulers.boundedElastic());
    }

    @ExceptionHandler({WebExchangeBindException.class}) // hibernate validation exceptions
    public Mono<ResponseEntity<Object>> handleBeanValidationExceptions(WebExchangeBindException exception) {
        return Mono.deferContextual(Mono::just).map(context -> {
            Map<String, String> errorDetails = new HashMap<>();
            exception.getFieldErrors().forEach((FieldError error) -> errorDetails.put(error.getField(), error.getDefaultMessage()));
            exception.getGlobalErrors().forEach((ObjectError error) -> errorDetails.put(error.getObjectName(), error.getDefaultMessage()));
            return new ResponseEntity<Object>(errorDetails, HttpStatus.UNPROCESSABLE_ENTITY);
        }).publishOn(Schedulers.boundedElastic());
    }
}
