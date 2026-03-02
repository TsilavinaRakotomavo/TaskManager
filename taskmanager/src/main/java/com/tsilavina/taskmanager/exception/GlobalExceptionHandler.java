package com.tsilavina.taskmanager.exception;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(EmailAlreadyUsedException.class)
  public ResponseEntity<ApiErrorResponse> handleEmailAlreadyUsed(
      EmailAlreadyUsedException ex,
      HttpServletRequest request) {
    return buildResponse(HttpStatus.CONFLICT, ex.getMessage(), request.getRequestURI(), null);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiErrorResponse> handleNotFound(
      ResourceNotFoundException ex,
      HttpServletRequest request) {
    return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request.getRequestURI(), null);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiErrorResponse> handleValidation(
      MethodArgumentNotValidException ex,
      HttpServletRequest request) {
    Map<String, String> errors = new LinkedHashMap<>();
    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      errors.put(error.getField(), error.getDefaultMessage());
    }
    return buildResponse(HttpStatus.BAD_REQUEST, "Validation failed", request.getRequestURI(), errors);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiErrorResponse> handleGeneric(
      Exception ex,
      HttpServletRequest request) {
    return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected server error", request.getRequestURI(), null);
  }

  private ResponseEntity<ApiErrorResponse> buildResponse(
      HttpStatus status,
      String message,
      String path,
      Map<String, String> validationErrors) {
    ApiErrorResponse error = new ApiErrorResponse(
        Instant.now(),
        status.value(),
        message,
        path,
        validationErrors);
    return ResponseEntity.status(status).body(error);
  }
}
