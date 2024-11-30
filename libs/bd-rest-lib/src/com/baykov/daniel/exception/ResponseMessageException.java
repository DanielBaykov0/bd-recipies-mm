package com.baykov.daniel.exception;

import com.baykov.daniel.payload.response.ResponseMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class ResponseMessageException extends ResponseStatusException {

    private final ResponseMessage responseMessage;

    public ResponseMessageException(HttpStatus status) {
        super(status);
        this.responseMessage = new ResponseMessage(1, status.name());
    }

    public ResponseMessageException(ResponseMessage responseMessage) {
        super(HttpStatus.valueOf(responseMessage.getHttpStatusCode()));
        this.responseMessage = responseMessage;
    }

    public ResponseMessageException(ResponseMessage responseMessage, Throwable e) {
        super(HttpStatus.valueOf(responseMessage.getHttpStatusCode()),
                HttpStatus.valueOf(responseMessage.getHttpStatusCode()).getReasonPhrase(),
                e);
        this.responseMessage = responseMessage;
    }

    public ResponseMessageException(String messageCode) {
        this(ResponseMessage.error(messageCode).build());
    }

    public ResponseMessageException(String messageCode, String message) {
        this(ResponseMessage.error(messageCode, message).build());
    }

    public ResponseMessageException(String messageCode, HttpStatus httpStatus) {
        this(ResponseMessage.error(messageCode, httpStatus).build());
    }
}
