package com.frb.infrastructure.api.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.frb.domain.exceptions.DomainException;
import com.frb.domain.exceptions.ExceptionWithErrors;
import com.frb.domain.exceptions.NotFoundException;
import com.frb.domain.exceptions.TokenNotFoundException;
import com.frb.domain.validation.Error;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.http.HttpTimeoutException;
import java.nio.channels.UnresolvedAddressException;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(final NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiError.from(ex));
    }

    @ExceptionHandler(value = DomainException.class)
    public ResponseEntity<?> handleDomainException(final DomainException ex) {
        return ResponseEntity.unprocessableEntity().body(ApiError.from(ex));
    }

    @ExceptionHandler(value = HttpTimeoutException.class)
    public ResponseEntity<?> handleHttpTimeoutException(final HttpTimeoutException ex) {
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(ApiError.with(ex.getMessage()));
    }

    @ExceptionHandler(value = UnresolvedAddressException.class)
    public ResponseEntity<?> handleUnresolvedAddressException(final UnresolvedAddressException ex) {
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(ApiError.with("connection error"));
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity<?> handleMissingServletRequestParameterException(final MissingServletRequestParameterException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(DefaultError.with(ex.getMessage()));
    }

    @ExceptionHandler(value = JsonParseException.class)
    public ResponseEntity<?> handleJsonParseException(final JsonParseException ex) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(DefaultError.with("gateway parse error"));
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<?> handleNullPointerException(final NullPointerException ex) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(DefaultError.with(ex.getMessage()));
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(final HttpMessageNotReadableException ex) {
        log.error(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(DefaultError.with("invalid request body"));
    }

    @ExceptionHandler(value = TokenNotFoundException.class)
    public ResponseEntity<?> handleTokenNotFoundException(final TokenNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(DefaultError.with(ex.getMessage()));
    }

    record ApiError(String message, List<Error> errors) {
        static ApiError from(final ExceptionWithErrors ex) {
            return new ApiError(ex.getMessage(), ex.getErrors());
        }

        static ApiError with(final String errorMessage) {
            return new ApiError(errorMessage, null);
        }
    }

    record DefaultError(String message) {
        static DefaultError with(final String errorMessage) {
            return new DefaultError(errorMessage);
        }
    }
}
