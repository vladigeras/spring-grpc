package ru.vladigeras.springgrpc.server.model.exception;

import java.io.Serial;

public class EntityNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 2969073618074267783L;

    public EntityNotFoundException(String message) {
        super(message);
    }
}
