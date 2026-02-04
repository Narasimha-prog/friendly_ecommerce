package com.lnreddy.friendlyecommerce.shared.exception;

import com.lnreddy.friendlyecommerce.user.domain.exception.EmailIsAlreadyExisted;
import com.lnreddy.friendlyecommerce.user.domain.exception.InvalidEmailException;
import com.lnreddy.friendlyecommerce.user.domain.exception.RoleNotFound;
import com.lnreddy.friendlyecommerce.user.domain.exception.UserNotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class RestGlobalExecutionHandler {


    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<String> handleInvalidCredentialsException(InvalidCredentialsException ex){

        log.warn("This Invalid email or password reason={}", ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }


    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<String> handleInvalidEmailException(InvalidEmailException ex){

        log.warn("This Invalid email reason={}", ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

    @ExceptionHandler(EmailIsAlreadyExisted.class)
    public ResponseEntity<String> handleEmailAlreadyExisted(EmailIsAlreadyExisted ex) {
        log.warn("This Email existed with Another User reason={}", ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

    @ExceptionHandler(RoleNotFound.class)
    public ResponseEntity<String> handleUserNotFound(RoleNotFound ex) {
        log.warn("ROLE_NOT_FOUND reason={}", ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFound ex) {
        log.warn("USER_NOT_FOUND reason={}", ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }


    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleAuthException(AuthenticationException ex) {
        log.warn("USER_LOGIN_FAILED reason={}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Invalid credentials");
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        log.error("UNEXPECTED_ERROR", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Something went wrong");
    }
}
