package br.com.treinaweb.adoteumpet.api.common.handlers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.treinaweb.adoteumpet.api.common.dtos.ErrorResponse;
import lombok.var;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException exception,
            HttpHeaders headers, 
            HttpStatus status, 
            WebRequest request
            ) {
                var body = ErrorResponse.builder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .cause(exception.getClass().getSimpleName())
                .message("Houveram erros de validação")
                .timestamp(LocalDateTime.now())
                .errors(convertFieldErrors(exception.getBindingResult().getFieldErrors()))
                .build();

                return new ResponseEntity<>(body, status);
    }

    private Map<String, List<String>> convertFieldErrors(List<FieldError> fieldErrors) {
        var errors = new HashMap<String, List<String>>();
        fieldErrors.stream().forEach(fieldError -> {
            var field = fieldError.getField();
            if (errors.containsKey(field)) {
                errors.get(field).add(fieldError.getDefaultMessage());
            } else {
                errors.put(
                    field, 
                    Stream.of(fieldError.getDefaultMessage()).collect(Collectors.toList())
                    );
            }
        
        });
        return errors;
    }
}
