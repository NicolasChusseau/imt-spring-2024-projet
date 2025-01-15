package org.imt.tournamentmaster.dto;

import org.imt.tournamentmaster.model.equipe.Equipe;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class Report {

    private Boolean success;

    private LocalDateTime dateTime;

    private String winnerName;

    private String errorMessage;

    public Report(Boolean success, String winnerName, String errorMessage) {
        this.success = success;
        ZoneId zoneId = ZoneId.of("Europe/Paris");
        this.dateTime = LocalDateTime.now(zoneId);
        this.winnerName = winnerName;
        this.errorMessage = errorMessage;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
