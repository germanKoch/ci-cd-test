package com.musala.musalatestapp.adapters.controller.interseptors;

import com.musala.musalatestapp.domain.exceptions.InvalidRequestException;
import com.musala.musalatestapp.domain.exceptions.InvalidStateException;
import com.musala.musalatestapp.domain.exceptions.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {InvalidRequestException.class})
    protected ResponseEntity<Object> handle(InvalidRequestException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<Object> handle(NotFoundException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {InvalidStateException.class})
    protected ResponseEntity<Object> handle(InvalidStateException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

}
