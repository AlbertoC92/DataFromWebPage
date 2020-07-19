package com.core.codeChallengeAlbertoC92.test.Exceptions;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;

public class EmptyDataBaseException extends RuntimeException{
    private int errorCode;
    private String errorMessage;
    private Logger logger;
    private String longMessage;
    private HttpStatus status;

    public EmptyDataBaseException(String message, HttpStatus status, String longMessage, Logger logger) {
        super(message);
        this.status = status;
        this.errorMessage = message;
        this.logger = logger;
        this.longMessage= longMessage;
        this.errorCode = status.value();
    }

    public HttpStatus getStatus(){return status;}

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return this.errorCode + " : " + this.getErrorMessage();
    }
}
