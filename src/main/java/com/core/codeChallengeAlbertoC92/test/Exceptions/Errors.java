package com.core.codeChallengeAlbertoC92.test.Exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Errors {

    private int code;
    private String message;

    public Errors (int code, String message) {
        this.code = code;
        this.message = message;
    }
}
