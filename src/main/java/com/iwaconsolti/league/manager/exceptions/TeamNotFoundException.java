package com.iwaconsolti.league.manager.exceptions;

public class TeamNotFoundException extends RuntimeException {
  public TeamNotFoundException(String message) {
    super(message);
  }
}
