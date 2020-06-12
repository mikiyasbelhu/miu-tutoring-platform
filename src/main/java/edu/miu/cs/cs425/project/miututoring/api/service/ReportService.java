package edu.miu.cs.cs425.project.miututoring.api.service;



import edu.miu.cs.cs425.project.miututoring.api.model.Course;
import edu.miu.cs.cs425.project.miututoring.api.model.Report;
import edu.miu.cs.cs425.project.miututoring.api.model.Student;
import edu.miu.cs.cs425.project.miututoring.api.model.TutorialGroup;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReportService {

    List<Report> getAllReports();
    Page<Report> getAllReportsPaged(int pageNo, Integer pageSize, String sortBy, Boolean sortDesc);
    List<Report> getAllReportsInATutorialGroup(TutorialGroup tutorialGroup);
    Report getReportById(Integer reportId);
    Report saveReport(Report report);
    Report updateReport(Report updatedReport, Integer reportId);
    void deleteReportById(Integer reportId);
    Page<Report> searchReports(String searchQuery, Student student, Course course, TutorialGroup tutorialGroup, int pageNo, Integer pageSize, String sortBy, Boolean sortDesc);
}
