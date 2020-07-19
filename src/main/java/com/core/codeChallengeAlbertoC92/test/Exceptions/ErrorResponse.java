package com.core.codeChallengeAlbertoC92.test.Exceptions;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "error")
@Data
public class ErrorResponse{

    private int status;
    private Errors error;

    public ErrorResponse(int status, Errors error) {
        super();
        this.status = status;
        this.error = error;
    }

}
