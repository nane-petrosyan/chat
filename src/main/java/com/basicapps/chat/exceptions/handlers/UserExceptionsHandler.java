package com.basicapps.chat.exceptions.handlers;

import com.basicapps.chat.exceptions.ErrorResponse;
import com.basicapps.chat.exceptions.exceptions.InvalidCredentialsException;
import com.basicapps.chat.exceptions.exceptions.InvalidTokenException;
import com.basicapps.chat.exceptions.exceptions.UserAlreadyExistsException;
import com.basicapps.chat.exceptions.exceptions.WrongDataFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionsHandler {
    @ExceptionHandler({WrongDataFormatException.class})
    public ResponseEntity<ErrorResponse> handleWrongDataFormat(WrongDataFormatException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Please enter a valid " + ex.getFieldName());
        errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleExistingUserException(UserAlreadyExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("User with specified " + ex.getFieldName() + " already exists");
        errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidCredentialsException.class, UsernameNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleInvalidCredentials() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        errorResponse.setMessage("Invalid credentials");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidTokenException.class})
    public ResponseEntity<ErrorResponse> handleInvalidToken() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setHttpStatus(HttpStatus.FORBIDDEN);
        errorResponse.setMessage("Please sign in first");
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }
}
