package br.com.poupex.starters.api.commons.support;

import br.com.poupex.starters.api.commons.exceptions.NegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ResourceExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseError handleValidationExceptions(MethodArgumentNotValidException ex) {
        var responseError = ResponseError.builder()
                                         .code(HttpStatus.BAD_REQUEST.value())
                                         .description("Campos da requisição inválidos.")
                                         .build();

        ex.getBindingResult().getAllErrors().forEach(e -> {
            String fieldName = ((FieldError) e).getField();
            String errorMessage = e.getDefaultMessage();
            responseError.getFields().put(fieldName, errorMessage);
        });

        return responseError;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseError handleConstraintViolationException(ConstraintViolationException ex) {
        var responseError = ResponseError.builder()
                                         .code(HttpStatus.BAD_REQUEST.value())
                                         .description(ex.getMessage())
                                         .build();

        ex.getConstraintViolations().forEach(cv -> {
            String fieldName = cv.getPropertyPath() == null ? "" : cv.getPropertyPath().toString();
            String errorMessage = cv.getMessage();
            responseError.getFields().put(fieldName, errorMessage);
        });

        return responseError;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NegocioException.class)
    public ResponseError handleNegocioExceptionException(NegocioException e) {
        return ResponseError.builder()
                            .code(HttpStatus.BAD_REQUEST.value())
                            .description(e.getMessage())
                            .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseError handleNotFoundExceptions(NoSuchElementException ex) {
        return ResponseError.builder()
                            .code(HttpStatus.NOT_FOUND.value())
                            .description("Recurso não encontrado.")
                            .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseError handleException(Exception e) {
        return ResponseError.builder()
                            .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .description("Ocorreu um erro interno em nosso servidor.")
                            .build();
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleValidationExceptions(ResponseStatusException ex) {
        return new ResponseEntity<>(ResponseError.builder()
                                                 .code(ex.getStatus().value())
                                                 .description(ex.getMessage())
                                                 .build(), ex.getStatus());
    }
}
