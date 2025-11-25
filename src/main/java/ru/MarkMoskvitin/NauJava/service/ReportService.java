package ru.MarkMoskvitin.NauJava.service;

import ru.MarkMoskvitin.NauJava.entity.Report;

public interface ReportService {
    Long addReport(Report report);
    String getContent(Report report);
    Report getReport(Long id);
    void createReport();

}
