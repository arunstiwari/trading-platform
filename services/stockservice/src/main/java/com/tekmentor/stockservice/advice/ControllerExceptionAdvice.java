package com.tekmentor.stockservice.advice;

import com.tekmentor.stockservice.exception.StockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ControllerExceptionAdvice extends ResponseEntityExceptionHandler {

    protected ResponseEntity<Object> handleException(RuntimeException exception, WebRequest request) {
        log.error("Exception : {}", exception.getMessage());
        StockException stockException = new StockException(exception.getMessage());

        return new ResponseEntity<>(stockException, HttpStatus.BAD_REQUEST);
    }
}
