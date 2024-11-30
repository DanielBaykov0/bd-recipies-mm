package com.baykov.daniel.recipes.payload;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;

@NoArgsConstructor
@AllArgsConstructor
public class StatusFieldError {

    private FieldError fieldError;

    public String getObjectName() {
        return fieldError.getObjectName();
    }

    public String getField() {
        return fieldError.getField();
    }

    public String getRejectedValue() {
        return fieldError.getRejectedValue() == null ? null : fieldError.getRejectedValue().toString();
    }

    public String getCode() {
        return fieldError.getCode();
    }

    public String[] getCodes() {
        return fieldError.getCodes();
    }

    public String getDefaultMessage() {
        return fieldError.getDefaultMessage();
    }
}
