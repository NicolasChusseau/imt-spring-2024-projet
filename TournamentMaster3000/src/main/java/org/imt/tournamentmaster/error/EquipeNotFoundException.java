package org.imt.tournamentmaster.error;

public class EquipeNotFoundException extends AbstractNotFoundException {
    public EquipeNotFoundException(String message) {
        super(message);
    }

    public EquipeNotFoundException(long id) {
        super("Equipe with id " + id + " not found");
    }

}
