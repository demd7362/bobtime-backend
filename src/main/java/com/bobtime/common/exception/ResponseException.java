package com.bobtime.common.exception;

import com.bobtime.common.enums.ResponseMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public class ResponseException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final ResponseMessage responseMessage;
}

