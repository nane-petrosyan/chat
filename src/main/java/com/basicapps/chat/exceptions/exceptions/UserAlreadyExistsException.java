package com.basicapps.chat.exceptions.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserAlreadyExistsException extends ResponseException {
    String fieldName;

    public String getFieldName() {
        return fieldName;
    }

    public UserAlreadyExistsException(String fieldName) {
        this.fieldName = fieldName;
    }
}
