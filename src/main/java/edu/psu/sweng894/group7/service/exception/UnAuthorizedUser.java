package edu.psu.sweng894.group7.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnAuthorizedUser extends RuntimeException {

    public UnAuthorizedUser(String exception) {
        super(exception);
    }
}
