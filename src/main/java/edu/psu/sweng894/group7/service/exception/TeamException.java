package edu.psu.sweng894.group7.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TeamException extends RuntimeException {

    public TeamException(String exception) {
        super(exception);
    }

}
