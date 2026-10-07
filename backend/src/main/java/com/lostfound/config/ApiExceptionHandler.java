package com.lostfound.config;
import org.springframework.http.*;import org.springframework.web.bind.annotation.*;import java.util.Map;
@RestControllerAdvice public class ApiExceptionHandler{
 @ExceptionHandler(IllegalArgumentException.class) public ResponseEntity<?> bad(IllegalArgumentException e){return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));}
 @ExceptionHandler(Exception.class) public ResponseEntity<?> err(Exception e){return ResponseEntity.status(500).body(Map.of("error","Server error","detail",e.getMessage()==null?"":e.getMessage()));}
}
