package com.baykov.daniel.exception;

import com.baykov.daniel.payload.response.ResponseMessage;
import jakarta.annotation.Nullable;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import java.sql.SQLException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleResourceNotFoundException(EntityNotFoundException ex, WebRequest request) {
       return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(ResponseMessageException.class)
    public ResponseEntity<Object> handleResponseMessageException(ResponseMessageException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getResponseMessage(), new HttpHeaders(), HttpStatus.valueOf(ex.getResponseMessage().getHttpStatusCode()), request);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, WebRequest request) {
        Throwable rootCause = getRootCause(ex);
        if (rootCause instanceof IllegalArgumentException) {
            ResponseMessage errorBody = new ResponseMessage(1, "VALIDATION_ERROR", rootCause.getMessage());
            return handleExceptionInternal(ex, errorBody, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
        }

        ResponseMessage errorBody = new ResponseMessage(1, "BAD_REQUEST", "Malformed JSON request");
        return handleExceptionInternal(ex, errorBody, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleValidationExceptions(Exception ex, WebRequest request) {
        BindingResult bindingResult = ex instanceof MethodArgumentNotValidException methodArgumentNotValidException
                ? methodArgumentNotValidException.getBindingResult()
                : ((BindException) ex).getBindingResult();
        ResponseMessage errorBody = createResponseMessageFromBindingResult(bindingResult);
        return handleExceptionInternal(ex, errorBody, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

//    @ExceptionHandler(AuthenticationException.class)
//    public ResponseEntity<ErrorDetails> handleAuthenticationException(){
//        ErrorDetails errorDetails = new ErrorDetails(INCORRECT_CREDENTIALS);
//        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
//    }

//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity<ErrorDetails> handleAccessDeniedException(AccessDeniedException exception) {
//        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage());
//        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
//    }
//
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public ResponseEntity<ErrorDetails> handleAccessDeniedException(DataIntegrityViolationException exception) {
//        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage());
//        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
//    }

    private ResponseMessage createResponseMessageFromBindingResult(BindingResult bindingResult) {
        ResponseMessage errorBody = new ResponseMessage(1, "VALIDATION_ERROR");

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorBody.addFieldError(fieldError);
        }

        for (ObjectError globalError : bindingResult.getGlobalErrors()) {
            errorBody.addGlobalError(globalError);
        }

        return errorBody;
    }

    private Throwable getRootCause(Throwable throwable) {
        Throwable cause = throwable.getCause();
        return (cause != null) ? getRootCause(cause) : throwable;
    }

    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex,
            @Nullable Object body,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        boolean logError = !(ex instanceof ResponseMessageException responseMessageException) || responseMessageException.getResponseMessage().getStatus() != 0;

        if (logError) {
            log.error(ex.getMessage(), ex);
        }

        if (ex instanceof UncategorizedSQLException && ex.getCause() instanceof SQLException cause) {
            int errorCode = cause.getErrorCode();

            if (errorCode == 100) {
                String messageCode = "CUSTOM_ERROR_CODE_STRING";
                String message = cause.getMessage();
                String[] messageParts = cause.getMessage().split("~");

                if (messageParts.length > 2) {
                    messageCode = messageParts[1];
                    message = messageParts[2];
                }

                body = new ResponseMessage(1, "ERROR", messageCode, 400, message);
                status = HttpStatus.OK;
            }
        }

        if (body == null) {
            body = new ResponseMessage(1, status.name());
        }

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, RequestAttributes.SCOPE_REQUEST);
        }

        return ResponseEntity
                .status(status)
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body);
    }
}
