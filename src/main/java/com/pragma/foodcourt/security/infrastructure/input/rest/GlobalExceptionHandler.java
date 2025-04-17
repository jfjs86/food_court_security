package com.pragma.foodcourt.security.infrastructure.input.rest;

import com.pragma.foodcourt.security.domain.exception.UserAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<WrapperResponse<Object>> handleGlobalException(Exception e) {
        log.error("Unexpected error: "+e);
        return handleInternalError(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<WrapperResponse<Object>> handleIllegalArgumentExceptionn(IllegalArgumentException e, HttpStatus httpStatus) {
        log.error("Unexpected error: "+e);
        return handleInternalError(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<WrapperResponse<Object>> handleUserAlreadyExistsException(UserAlreadyExistsException e){
        log.error("UserAlreadyExistsException: "+e);
        return handleInternalError(e, HttpStatus.CONFLICT);
    }

    private ResponseEntity<WrapperResponse<Object>> handleInternalError(Exception e, HttpStatus httpStatus) {
        WrapperResponse<Object> response = new WrapperResponse<>(false, e.getLocalizedMessage(), null);
        return new ResponseEntity<>(response, httpStatus);
    }

}