package com.bobtime.dto.response;


import com.bobtime.common.enums.dialog.DialogMessage;
import com.bobtime.common.enums.dialog.ServerMessage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class ResponseDTO {
    private HttpStatus httpStatus;
    private Object data;
    private DialogMessage message;
    private int statusCode;

    public static ResponseBuilder entityBuilder() {
        return new ResponseBuilder();
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ResponseBuilder {
        private HttpStatus httpStatus;
        private Object data;
        private DialogMessage message;
        private int statusCode;

        public ResponseBuilder httpStatus(HttpStatus httpStatus) {
            this.httpStatus = httpStatus;
            this.statusCode = httpStatus.value();
            return this;
        }

        public ResponseBuilder statusCode(int statusCode) {
            this.httpStatus = HttpStatus.valueOf(statusCode);
            this.statusCode = statusCode;
            return this;
        }

        public ResponseBuilder data(Object data) {
            this.data = data;
            return this;
        }

        public ResponseBuilder message(DialogMessage message) {
            this.message = message;
            return this;
        }

        public ResponseEntity<ResponseDTO> build() {
            if (httpStatus == null) {
                log.error("cannot build response cause invalid `HttpStatus` found");
                message = ServerMessage.INTERNAL_SERVER_ERROR;
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            }
            return new ResponseEntity<>(new ResponseDTO(httpStatus, data, message, statusCode), httpStatus);
        }
    }
}
