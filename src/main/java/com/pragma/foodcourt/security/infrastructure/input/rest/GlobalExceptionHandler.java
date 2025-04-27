package com.pragma.foodcourt.security.infrastructure.input.rest;

import com.pragma.foodcourt.security.domain.exception.InvalidUserException;
import com.pragma.foodcourt.security.domain.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String UNEXPECTED_ERROR = "Unexpected error: ";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<WrapperResponse<Object>> handleGlobalException(Exception e) {
        log.error(UNEXPECTED_ERROR + e);
        return handleInternalError(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<WrapperResponse<Object>> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error(UNEXPECTED_ERROR + e);
        return handleInternalError(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity<WrapperResponse<Object>> handleInvalidUserException(InvalidUserException e) {
        log.error("" + e);
        return handleInternalError(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<WrapperResponse<Object>> handleUserNotFoundException(UserNotFoundException e) {
        log.error("" + e);
        return handleInternalError(e, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<WrapperResponse<Object>> handleInternalError(Exception e, HttpStatus httpStatus) {
        WrapperResponse<Object> response = new WrapperResponse<>(false, e.getMessage(), null);
        return new ResponseEntity<>(response, httpStatus);
    }
}