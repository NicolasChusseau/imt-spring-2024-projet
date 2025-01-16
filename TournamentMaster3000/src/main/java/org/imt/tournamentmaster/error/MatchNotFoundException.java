package org.imt.tournamentmaster.error;

public class MatchNotFoundException extends AbstractNotFoundException {
    public MatchNotFoundException(String message) {
        super(message);
    }

    public MatchNotFoundException(long id) {
        super("Match with id " + id + " not found");
    }

}
