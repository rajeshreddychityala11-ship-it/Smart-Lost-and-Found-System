package com.lostfound.config;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> bad(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(Map.of("error", safe(e.getMessage())));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validation(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldErrors().stream()
                .map(x -> x.getField() + ": " + x.getDefaultMessage())
                .collect(Collectors.joining("; "));
        return ResponseEntity.badRequest().body(Map.of("error", message.isBlank() ? "Invalid request" : message));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> dataConflict(DataIntegrityViolationException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("error", "The submitted data conflicts with an existing database record."));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> notFound(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Resource not found"));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> unreadable(HttpMessageNotReadableException e) {
        return ResponseEntity.badRequest().body(Map.of("error", "Invalid request data. Check the date/type values and JSON format."));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> err(Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(500).body(Map.of(
                "error", "Server error",
                "detail", safe(e.getMessage())
        ));
    }

    private String safe(String value) {
        return value == null || value.isBlank() ? "Unexpected error" : value;
    }
}
