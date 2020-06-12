package edu.miu.cs.cs425.project.miututoring.api.controller;

import edu.miu.cs.cs425.project.miututoring.api.model.Course;
import edu.miu.cs.cs425.project.miututoring.api.model.Report;
import edu.miu.cs.cs425.project.miututoring.api.model.Student;
import edu.miu.cs.cs425.project.miututoring.api.model.TutorialGroup;
import edu.miu.cs.cs425.project.miututoring.api.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/report", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReportController {

    @Autowired
    ReportService reportService;


    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping(value = "/list")
    public Page<Report> listReports(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer itemsPerPage,
                                    @RequestParam String sortBy, @RequestParam(defaultValue = "false") Boolean sortDesc) {
        return reportService.getAllReportsPaged(page, itemsPerPage, sortBy, sortDesc);
    }

    @GetMapping(value = "/getbygroup")
    public List<Report> getReportInATutorialGroup(TutorialGroup tutorialGroup) {
        return reportService.getAllReportsInATutorialGroup(tutorialGroup);
    }

    @GetMapping(value = "/get/{reportId}")
    public Report getReportById(@PathVariable Integer reportId) {
        return reportService.getReportById(reportId);
    }

    @PutMapping(value = "/edit/{reportId}")
    public Report updateReport(@Valid @RequestBody Report updatedReport, @PathVariable Integer reportId) {
        return reportService.updateReport(updatedReport, reportId);
    }

    @DeleteMapping(value = "/delete/{reportId}")
    public void deleteReportById(@PathVariable Integer reportId) {
        reportService.deleteReportById(reportId);
    }

    @PostMapping(value = "/save")
    public Report saveReport(@Valid @RequestBody Report report) {
        return reportService.saveReport(report);
    }

    @GetMapping(value = "/search")
    public Page<Report> searchReport(@RequestParam String searchQuery, @RequestParam Student student, @RequestParam Course course, @RequestParam TutorialGroup tutorialGroup, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer itemsPerPage,
                                     @RequestParam String sortBy, @RequestParam(defaultValue = "false") Boolean sortDesc) {
        return reportService.searchReports(searchQuery, student, course, tutorialGroup, page, itemsPerPage, sortBy, sortDesc);
    }

    @GetMapping(value = "/get/{studentId}")
    public Report getReportByStudentId(@PathVariable Long studentId) {
        return reportService.getReportByStudentId(studentId);
    }

}
