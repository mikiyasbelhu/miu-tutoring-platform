package edu.miu.cs.cs425.project.miututoring.api.service.impl;

import edu.miu.cs.cs425.project.miututoring.api.model.Report;
import edu.miu.cs.cs425.project.miututoring.api.model.TutorialGroup;
import edu.miu.cs.cs425.project.miututoring.api.repository.ReportRepository;
import edu.miu.cs.cs425.project.miututoring.api.service.ReportService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    ReportRepository reportRepository;

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public List<Report> getAllReports() {
        return (List<Report>) reportRepository.findAll();
    }

    @Override
    public List<Report> getAllReportsInATutorialGroup(TutorialGroup tutorialGroup) {
        return reportRepository.findAllByTutorialGroupEquals(tutorialGroup);
    }

    @Override
    public Report saveReport(Report report) {
        return reportRepository.save(report);
    }

    @Override
    public Report updateReport(Report updatedReport, Integer reportId) {
        return reportRepository.findById(reportId).map(report-> {return reportRepository.save(report);})
                .orElseGet(()-> reportRepository.save(updatedReport));
    }

    @Override
    public void deleteReportById(Integer reportId) {
        reportRepository.deleteById(reportId);
    }
}
