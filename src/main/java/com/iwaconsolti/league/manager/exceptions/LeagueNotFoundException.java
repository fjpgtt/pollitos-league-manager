package com.iwaconsolti.league.manager.exceptions;

public class LeagueNotFoundException extends RuntimeException {
    public LeagueNotFoundException(String message) {
        super(message);
    }
}
