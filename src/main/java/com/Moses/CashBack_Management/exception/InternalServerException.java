package com.Moses.CashBack_Management.exception;

import org.springframework.http.HttpStatus;

public class InternalServerException extends RuntimeException{

    private HttpStatus status;

    public InternalServerException(String message) {
        super(message);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;


    }

}
