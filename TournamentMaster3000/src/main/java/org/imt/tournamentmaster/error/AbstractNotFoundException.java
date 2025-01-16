package org.imt.tournamentmaster.error;

public abstract class AbstractNotFoundException extends RuntimeException {
    public AbstractNotFoundException(String message) {
        super(message);
    }

}
