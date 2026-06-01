package com.bootcamp.facturacion.config;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleAllExceptions(Exception ex, WebRequest request) {
        
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        ResponseStatus responseStatus = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
        if (responseStatus != null) {
            status = responseStatus.value();
        } else {
            String exClassName = ex.getClass().getSimpleName();
            
            switch (exClassName) {
                case "DataIntegrityViolationException":
                case "HttpMessageNotReadableException":
                case "MethodArgumentNotValidException":
                case "ConstraintViolationException":
                    status = HttpStatus.BAD_REQUEST;
                    break;
                
                case "NoHandlerFoundException":
                case "NoResourceFoundException":
                    status = HttpStatus.NOT_FOUND;
                    break;
                
                case "HttpRequestMethodNotSupportedException":
                    status = HttpStatus.METHOD_NOT_ALLOWED;
                    break;
                
                default:
                    break;
            }
        }

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", ex.getMessage());
        body.put("path", request.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(body, status);
    }
}
