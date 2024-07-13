package com.challenge.app.challenge.exception;

public class TeamNotSavedException extends RuntimeException {
    public TeamNotSavedException() {
        super("La solicitud es inválida");
    }
}
