package org.imt.tournamentmaster.dto;

import java.util.ArrayList;
import java.util.List;

public class ImportReport {

    private static final String BASIC_GLOBAL_MESSAGE_TEMPLATE = "Imported successfully %d results out of %d";

    private List<Report> reportList;

    private String globalMessage;

    public ImportReport() {
        reportList = new ArrayList<>();
    }

    public List<Report> getReportList() {
        return reportList;
    }

    public void setReportList(List<Report> reportList) {
        this.reportList = reportList;
    }

    public void addReport(Report report) {
        reportList.add(report);
    }

    public String getGlobalMessage() {
        return globalMessage;
    }

    public void setGlobalMessage(String globalMessage) {
        this.globalMessage = globalMessage;
    }

    public void setBasicGlobalMessageTemplate() {
        Long successCount = reportList.stream().filter(Report::getSuccess).count();
        globalMessage = String.format(BASIC_GLOBAL_MESSAGE_TEMPLATE, successCount, reportList.size());
    }
}
