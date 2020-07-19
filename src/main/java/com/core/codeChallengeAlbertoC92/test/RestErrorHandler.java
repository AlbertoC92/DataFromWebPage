package com.core.codeChallengeAlbertoC92.test;

import com.core.codeChallengeAlbertoC92.test.Exceptions.EmptyDataBaseException;
import com.core.codeChallengeAlbertoC92.test.Exceptions.ErrorResponse;
import com.core.codeChallengeAlbertoC92.test.Exceptions.Errors;
import com.core.codeChallengeAlbertoC92.test.Exceptions.ItemAlreadyExistException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@SuppressWarnings({"unchecked","rawtypes"})
public class RestErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ItemAlreadyExistException.class})
    public ResponseEntity<ErrorResponse> handleItemAlreadyExistException(ItemAlreadyExistException ex) {
        Errors errors = new Errors(00001,ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(ex.getErrorCode(), errors);
        return new ResponseEntity(error, ex.getStatus());
    }

    @ExceptionHandler({EmptyDataBaseException.class})
    public ResponseEntity<ErrorResponse> handleEmptyDataBaseException(EmptyDataBaseException ex) {
        Errors errors = new Errors(00002,ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(ex.getErrorCode(), errors);
        return new ResponseEntity(error, ex.getStatus());
    }
}
