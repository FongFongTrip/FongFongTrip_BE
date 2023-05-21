package com.ssafy.fongfongtrip.global.advice;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    @Value("${client.url}")
    private String clientUrl;

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> entityNotFoundExceptionHandler(Exception e) {
        log.info("EntityNotFoundException: {}", e.getMessage());
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public void expiredJwtExceptionHandler(Exception e, HttpServletResponse response) throws IOException {
        log.info("ExpiredJwtException: {}", e.getMessage());
        response.sendRedirect(clientUrl + "/login");
//        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> runtimeExceptionHandler(Exception e) {
        log.info("RuntimeException: {}", e.getMessage());
        e.printStackTrace();
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> exceptionHandler(Exception e) {
        log.info("Exception: {}", e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
