package com.lnreddy.friendlyecommerce.shared.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class RestExecutionHandler {





    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> toHandleAllExceptions(Exception exception){
    log.error("Error from Exception method :",  exception);
            return ResponseEntity.internalServerError().body(exception.getMessage());
    }
}
