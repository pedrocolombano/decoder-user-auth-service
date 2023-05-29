package com.ead.userauth.exception.handler;

import com.ead.userauth.exception.InvalidDataException;
import com.ead.userauth.exception.InvalidSubscriptionException;
import com.ead.userauth.exception.ProxyException;
import com.ead.userauth.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> handleResourceNotFound(final ResourceNotFoundException exception,
                                                                final HttpServletRequest request) {
        final HttpStatus notFoundStatus = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(notFoundStatus).body(createResponseBody(notFoundStatus, exception, request));
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<StandardError> handleInvalidData(final InvalidDataException exception,
                                                           final HttpServletRequest request) {
        final HttpStatus unprocessableEntityStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        return ResponseEntity.status(unprocessableEntityStatus).body(createResponseBody(unprocessableEntityStatus, exception, request));
    }

    @ExceptionHandler(ProxyException.class)
    public ResponseEntity<StandardError> handleInvalidData(final ProxyException exception,
                                                           final HttpServletRequest request) {
        final HttpStatus badGatewayStatus = HttpStatus.BAD_GATEWAY;
        return ResponseEntity.status(badGatewayStatus).body(createResponseBody(badGatewayStatus, exception, request));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> handleArgumentNotValid(final MethodArgumentNotValidException exception,
                                                           final HttpServletRequest request) {
        final HttpStatus badRequestStatus = HttpStatus.BAD_REQUEST;
        final StandardError response = createResponseBody(badRequestStatus, exception, request);
        response.getErrors().addAll(createFieldErrorList(exception));

        return ResponseEntity.status(badRequestStatus).body(response);
    }

    @ExceptionHandler(InvalidSubscriptionException.class)
    public ResponseEntity<StandardError> handleInvalidSubscription(final InvalidSubscriptionException exception,
                                                                   final HttpServletRequest request) {
        final HttpStatus badGatewayStatus = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(badGatewayStatus).body(createResponseBody(badGatewayStatus, exception, request));
    }

    private StandardError createResponseBody(final HttpStatus status,
                                             final Exception exception,
                                             final HttpServletRequest request) {
        return StandardError.builder()
                .statusCode(status.value())
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .build();
    }

    private List<FieldError> createFieldErrorList(final MethodArgumentNotValidException exception) {
        return exception.getBindingResult()
                .getFieldErrors().stream()
                .map(error -> FieldError.builder()
                        .field(error.getField())
                        .errorMessage(error.getDefaultMessage())
                        .build())
                .collect(Collectors.toList());
    }

}
