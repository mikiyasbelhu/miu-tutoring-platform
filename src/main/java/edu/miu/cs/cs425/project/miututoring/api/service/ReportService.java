package edu.miu.cs.cs425.project.miututoring.api.service;



import edu.miu.cs.cs425.project.miututoring.api.model.Report;
import edu.miu.cs.cs425.project.miututoring.api.model.TutorialGroup;

import java.util.List;

public interface ReportService {

    List<Report> getAllReports();
    List<Report> getAllReportsInATutorialGroup(TutorialGroup tutorialGroup);
    Report saveReport(Report report);
    Report updateReport(Report updatedReport, Integer reportId);
    void deleteReportById(Integer reportId);
}
