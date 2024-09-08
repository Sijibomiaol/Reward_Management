
package com.Moses.CashBack_Management.exception;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.concurrent.TimeoutException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RewardNotFoundException.class)
    public ResponseEntity<Object> handleRewardNotFoundException(
            RewardNotFoundException ex) {
        // Custom response body
        ErrorMessage ErrorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(ErrorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InternalServerException.class, TimeoutException.class})
    public ResponseEntity<Object> handleInternalServerError(
            Exception ex) {
        // Custom response body for internal server error or timeout
        ErrorMessage ErrorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(ErrorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Other exception handlers as needed

    // Default handler for all other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(
            Exception ex) {
        // Generic error response
        ErrorMessage ErrorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(ErrorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
}
}