package com.tekmentor.authenticationservice.advice;

import com.tekmentor.authenticationservice.exception.TradingAppAccessException;
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
        TradingAppAccessException tradingAppAccessException = new TradingAppAccessException(exception.getMessage());

        return new ResponseEntity<>(tradingAppAccessException, HttpStatus.BAD_REQUEST);
    }
}
