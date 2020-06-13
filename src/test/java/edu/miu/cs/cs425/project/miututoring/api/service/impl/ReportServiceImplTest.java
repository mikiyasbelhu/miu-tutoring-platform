package edu.miu.cs.cs425.project.miututoring.api.service.impl;

import edu.miu.cs.cs425.project.miututoring.api.AbstractMiuTutoringComponentTest;
import edu.miu.cs.cs425.project.miututoring.api.model.Report;
import edu.miu.cs.cs425.project.miututoring.api.service.ReportService;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
class ReportServiceImplTest extends AbstractMiuTutoringComponentTest {

    @Autowired
    private ReportService reportService;

    @BeforeEach
    void setUp() {
        logger.info("ReportServiceImplTest started");
    }

    @AfterEach
    void tearDown() {
        logger.info("ReportServiceImplTest completed");
    }

    @Test
    void getAllReports() {
        List<Report> reports = reportService.getAllReports();
        Assert.assertNotNull("Failure: expected reports not to be null", reports);
        Assert.assertEquals("Failure: expected 2 reports", 2,reports.size());
        logger.info("Reports list data: " + Arrays.toString(reports.toArray()));
    }

    @Test
    void getAllReportsInATutorialGroup() {
        Long test = new Long(1);
        List<Report> reports = reportService.getAllReportsInATutorialGroup(test);
        Assert.assertNotNull("Failure: expected reports not to be null", reports);
        Assert.assertEquals("Failure: expected 1 report", 1, reports.size());
        logger.info("Reports list data: " + Arrays.toString(reports.toArray()));
    }

    @Test
    void getReportById() {
        Integer reportId = 1;
        Report report = reportService.getReportById(reportId);
        Assert.assertNotNull("Failure: expected report not to be null", report);
        Assert.assertEquals("Failure: expected report ID to be the same", reportId, report.getReportId());
        logger.info("Report data: " + report);
    }

    @Test
    public void testGetReportByIdForInvalidId() {
        Integer reportId = Integer.MAX_VALUE;
        Report report = reportService.getReportById(reportId);
        Assert.assertNull("Failure: expected null", report);
        logger.info("Book data: " + report);
    }

    @Test
    void saveReport() {



    }

    @Test
    void updateReport() {
    }

    @Test
    void deleteReportById() {
    }
}