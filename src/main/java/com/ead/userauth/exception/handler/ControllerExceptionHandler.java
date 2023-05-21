package com.ead.userauth.exception.handler;

import com.ead.userauth.exception.InvalidDataException;
import com.ead.userauth.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

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
        final HttpStatus notFoundStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        return ResponseEntity.status(notFoundStatus).body(createResponseBody(notFoundStatus, exception, request));
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

}