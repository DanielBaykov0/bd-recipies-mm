package com.baykov.daniel.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseMessage {

    private static final String SUCCESS_CODE = "SUCCESS";
    private static final String ERROR_CODE = "ERROR";
    private static final int STATUS_SUCCESS = 0;
    private static final int STATUS_ERROR = 1;

    private String externalRef;
    private String message;
    private String messageCode;
    private String refNo;
    private int status;
    private String statusDesc;
    private int httpStatusCode;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<FieldError> fieldErrors;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ObjectError> globalErrors;

    public ResponseMessage() {
        super();
        this.status = STATUS_SUCCESS;
        this.httpStatusCode = HttpStatus.OK.value();
        this.fieldErrors = new ArrayList<>();
        this.globalErrors = new ArrayList<>();
    }

    public ResponseMessage(int status, String messageCode, int httpStatusCode) {
        this();
        this.status = status;
        this.httpStatusCode = httpStatusCode;
        this.messageCode = messageCode;
    }

    public ResponseMessage(int status, String messageCode) {
        this(status, messageCode, status == 0 ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value());
    }

    public ResponseMessage(int status, String messageCode, String message) {
        this(status, messageCode);
        this.message = message;
    }

    public ResponseMessage(int status, String statusDesc, String messageCode, String message, String refNo, String externalRef) {
        this(status, messageCode);
        this.statusDesc = statusDesc;
        this.message = message;
        this.refNo = refNo;
        this.externalRef = externalRef;
    }

    public ResponseMessage(int status, String statusDesc, String messageCode, int httpStatusCode, String message) {
        this(status, statusDesc, messageCode, message, "", "");
        this.httpStatusCode = httpStatusCode;
    }

    public static ResponseMessage success() {
        return ResponseMessage.builder()
                .status(STATUS_SUCCESS)
                .messageCode(SUCCESS_CODE)
                .httpStatusCode(HttpStatus.OK.value())
                .build();
    }

    public static ResponseMessageBuilder error() {
        return ResponseMessage.builder()
                .status(STATUS_ERROR)
                .messageCode(ERROR_CODE)
                .httpStatusCode(HttpStatus.BAD_REQUEST.value());
    }

    public static ResponseMessageBuilder error(String messageCode) {
        return ResponseMessage.builder()
                .status(STATUS_ERROR)
                .messageCode(messageCode)
                .httpStatusCode(HttpStatus.BAD_REQUEST.value());
    }

    public static ResponseMessageBuilder error(String messageCode, String message) {
        return ResponseMessage.builder()
                .status(STATUS_ERROR)
                .messageCode(messageCode)
                .message(message)
                .httpStatusCode(HttpStatus.BAD_REQUEST.value());
    }

    public static ResponseMessageBuilder error(String messageCode, HttpStatus httpStatus) {
        return ResponseMessage.builder()
                .status(STATUS_ERROR)
                .messageCode(messageCode)
                .httpStatusCode(httpStatus.value());
    }

    public static ResponseMessageBuilder error(String messageCode, String message, HttpStatus httpStatus) {
        return ResponseMessage.builder()
                .status(STATUS_ERROR)
                .messageCode(messageCode)
                .message(message)
                .httpStatusCode(httpStatus.value());
    }

    public void addFieldError(FieldError fieldError) {
        this.fieldErrors.add(fieldError);
    }

    public void addGlobalError(ObjectError globalError) {
        this.globalErrors.add(globalError);
    }
}
