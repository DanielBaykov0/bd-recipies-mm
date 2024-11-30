package com.baykov.daniel.recipes.payload;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.validation.ObjectError;

@NoArgsConstructor
@AllArgsConstructor
public class StatusObjectError {

    private ObjectError objectError;

    public String getObjectName() {
        return objectError.getObjectName();
    }

    public String getCode() {
        return objectError.getCode();
    }

    public String[] getCodes() {
        return objectError.getCodes();
    }

    public String getDefaultMessage() {
        return objectError.getDefaultMessage();
    }
}
