package org.registrationservice.infrastructure.adapter.in.web;

import lombok.extern.slf4j.Slf4j;
import org.registrationservice.infrastructure.adapter.in.web.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> handleIllegalArgument(IllegalArgumentException ex) {
        log.warn("Invalid argument: {}", ex.getMessage());
        return new ResponseEntity<>(
                new ErrorResponseDto(ex.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDto> handleMessageNotReadable(HttpMessageNotReadableException ex) {
        log.warn("Invalid request body: {}", ex.getMessage());
        return new ResponseEntity<>(
                new ErrorResponseDto("Invalid request body: check that all fields have valid values"),
                HttpStatus.BAD_REQUEST
        );
    }

}
