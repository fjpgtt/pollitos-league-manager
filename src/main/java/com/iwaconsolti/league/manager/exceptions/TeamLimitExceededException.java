package com.iwaconsolti.league.manager.exceptions;

public class TeamLimitExceededException extends RuntimeException {
    public TeamLimitExceededException(String message) {
        super(message);
    }
}
