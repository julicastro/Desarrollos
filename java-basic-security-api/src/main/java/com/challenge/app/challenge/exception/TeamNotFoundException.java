package com.challenge.app.challenge.exception;

public class TeamNotFoundException extends RuntimeException {
    public TeamNotFoundException() {
        super("Equipo no encontrado");
    }
}