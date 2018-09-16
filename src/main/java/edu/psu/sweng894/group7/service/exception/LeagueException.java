package edu.psu.sweng894.group7.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LeagueException extends RuntimeException {

    public LeagueException(String exception) {
        super(exception);
    }
}
