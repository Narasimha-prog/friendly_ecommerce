package com.lnreddy.friendlyecommerce.shared.exception;

import com.lnreddy.friendlyecommerce.shared.dto.ErrorResponse;
import com.lnreddy.friendlyecommerce.user.domain.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class RestGlobalExceptionHandler {


    @ExceptionHandler(WeakPasswordException.class)
    public ResponseEntity<ErrorResponse> handleWeakPasswordException (WeakPasswordException ex){

        log.warn("Weak password  reason={}", ex.getMessage());

        return ResponseEntity.status(ex.getStatus()).body(ErrorResponse.builder().message(ex.getMessage()).field(ex.getFiled()).build());
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(InvalidCredentialsException ex){

        log.warn("This Invalid email or password reason={}", ex.getMessage());

        return ResponseEntity.status(ex.getStatus()).body(ErrorResponse.builder().message(ex.getMessage()).field(ex.getField()).build());
    }

    @ExceptionHandler(InvalidAddressException.class)
    public ResponseEntity<ErrorResponse>  handleInvalidAddressException(InvalidAddressException ex){

        log.warn("This Invalid Address  reason={}", ex.getMessage());


        return ResponseEntity.
                status(ex.getStatus()).body(ErrorResponse.builder().field(ex.getField()).message(ex.getMessage()).build());
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<ErrorResponse> handleInvalidEmailException(InvalidEmailException ex){

        log.warn("This Invalid email reason={}", ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(ErrorResponse.builder().field(ex.getField()).message(ex.getMessage()).build());
    }

    @ExceptionHandler(EmailIsAlreadyExisted.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyExisted(EmailIsAlreadyExisted ex) {

        log.warn("This Email existed with Another User reason={}", ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(ErrorResponse.builder().field(ex.getFiled()).message(ex.getMessage()).build());
    }

    @ExceptionHandler(RoleNotFound.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(RoleNotFound ex) {

        log.warn("ROLE_NOT_FOUND reason={}", ex.getMessage());

        return ResponseEntity.status(ex.getStatus()).body(ErrorResponse.builder().field(ex.getField()).message(ex.getMessage()).build());
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFound ex) {
        log.warn("USER_NOT_FOUND reason={}", ex.getMessage());

        return ResponseEntity.status(ex.getStatus()).body(ErrorResponse.builder().field(ex.getField()).message(ex.getMessage()).build());
    }


    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthException(AuthenticationException ex) {
        log.warn("USER_LOGIN_FAILED reason={}", ex.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorResponse.builder().field("Authentication").message(ex.getMessage()).build());
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        log.error("UNEXPECTED_ERROR", ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.builder()
                              .message("Something went wrong. Please try again later.")
                              .build());
    }

}
