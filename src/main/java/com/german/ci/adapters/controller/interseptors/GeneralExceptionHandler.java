package com.german.ci.adapters.controller.interseptors;

import com.german.ci.domain.exceptions.InvalidRequestException;
import com.german.ci.domain.exceptions.InvalidStateException;
import com.german.ci.domain.exceptions.NotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Log4j2
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {InvalidRequestException.class})
    protected ResponseEntity<Object> handle(InvalidRequestException ex, WebRequest request) {
        return handleException(ex, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<Object> handle(NotFoundException ex, WebRequest request) {
        return handleException(ex, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {InvalidStateException.class})
    protected ResponseEntity<Object> handle(InvalidStateException ex, WebRequest request) {
        return handleException(ex, HttpStatus.CONFLICT, request);
    }

    private ResponseEntity<Object> handleException(RuntimeException ex, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), status, request);
    }

}
