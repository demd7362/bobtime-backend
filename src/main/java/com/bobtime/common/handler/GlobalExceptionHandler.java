package com.bobtime.common.handler;

import com.bobtime.common.exception.ResponseException;
import com.bobtime.dto.response.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler({ResponseException.class})
    public ResponseEntity<ResponseDTO> handleResponseException(ResponseException e) {
        return ResponseDTO.entityBuilder()
                .httpStatus(e.getHttpStatus())
                .message(e.getDialogMessage())
                .build();
    }
}
