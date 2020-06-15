package edu.miu.cs.cs425.project.miututoring.api.service.impl;

import edu.miu.cs.cs425.project.miututoring.api.AbstractMiuTutoringComponentTest;
import edu.miu.cs.cs425.project.miututoring.api.model.*;
import edu.miu.cs.cs425.project.miututoring.api.service.*;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


@Transactional
class ReportServiceImplTest extends AbstractMiuTutoringComponentTest {

    @Autowired
    private ReportService reportService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TutorialGroupService tutorialGroupService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private FacultyService facultyService;
    @Autowired
    private SectionService sectionService;

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
        Assert.assertEquals("Failure: expected 2 reports", 2, reports.size());
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
    void saveReport() throws Exception {
        Student student1 = new Student("mock@miu.edu", "mock", "000-61-0003", "Alem", "Lemma", "Girum", 3.45, LocalDate.of(2020, 5, 24));
        studentService.registerStudent(student1);
        Faculty faculty1 = new Faculty("mocker@miu.edu", "faculty", "Michael", "A", "Zijlstra", "MPP CS420");
        facultyService.registerFaculty(faculty1);
        Course course = new Course("CS420", "MPP", 4);
        courseService.saveCourse(course);
        Section section = new Section("CS420-2020-06-01", "Library 110", "2020-07", course, faculty1);
        sectionService.saveSection(section);
        TutorialGroup tutorialGroup2 = new TutorialGroup("CS420", section);
        tutorialGroupService.registerTutorialGroup(tutorialGroup2);
        Report test = new Report(student1, tutorialGroup2, course, "This student knows well the different Software Engineering concepts");
        Report actual = reportService.saveReport(test);
        Assert.assertEquals("Failure: expected to be equal", test.getStudent().getStudentNumber(), actual.getStudent().getStudentNumber());
        Assert.assertNotNull("Failure: expected saved report not to be null", actual);
        Assert.assertNotNull("Failure: expected reportId not to be null", actual.getReportId());
        Assert.assertNotNull("Failure: expected report to return a student", actual.getStudent());
        List<Report> reports = reportService.getAllReports();
        Assert.assertEquals("Failure: expected 3 reports", 3, reports.size());
        logger.info("Report list Data: " + Arrays.toString(reports.toArray()));
    }

    @Test
    void updateReport() throws Exception {
        Student student1 = new Student("mock@miu.edu", "mock", "000-61-0003", "Alem", "Lemma", "Girum", 3.45, LocalDate.of(2020, 5, 24));
        studentService.registerStudent(student1);
        Faculty faculty1 = new Faculty("mocker@miu.edu", "faculty", "Michael", "A", "Zijlstra", "MPP CS420");
        facultyService.registerFaculty(faculty1);
        Course course = new Course("CS420", "MPP", 4);
        courseService.saveCourse(course);
        Section section = new Section("CS420-2020-06-01", "Library 110", "2020-07", course, faculty1);
        sectionService.saveSection(section);
        TutorialGroup tutorialGroup2 = new TutorialGroup("CS420", section);
        tutorialGroupService.registerTutorialGroup(tutorialGroup2);
        Report update = new Report(student1, tutorialGroup2, course, "This student knows well the different Software Engineering concepts");
        Report updated = reportService.updateReport(update,1);
        Assert.assertNotNull("Failure: expected updated report not to be null", updated);
        Assert.assertNotNull("Failure: expected updated reportId not to be null", updated.getReportId());
        Assert.assertEquals("Failure: expected student number to be the same", student1.getStudentNumber(),updated.getStudent().getStudentNumber());
        Assert.assertEquals("Failure: expected tutorial group number to be the same", tutorialGroup2.getTutorialGroupNumber(), updated.getTutorialGroup().getTutorialGroupNumber());
        Assert.assertEquals("Failure: expected course number to be the same", course.getCourseNumber(), updated.getCourse().getCourseNumber());
        logger.info("Report Data: " + updated);
    }

    @Test
    void deleteReportById() {
    Report toBeDeleted = reportService.getReportById(1);
    Assert.assertNotNull("Failure: expected report not to be null", toBeDeleted);
    reportService.deleteReportById(1);
    List<Report> reports = reportService.getAllReports();
    Assert.assertEquals("Failure: expected 1 report", 1, reports.size());
    Report deleted = reportService.getReportById(1);
    Assert.assertNull("Failure: expected Report to be deleted", deleted);
    }

    @Test
    void findReportByStudentId(){
        Long studentId = 4L;
        List<Report> actual = reportService.getReportByStudentId(studentId);
        Assert.assertNotNull("Failure: expected report not to be null", actual);
        Assert.assertEquals("Failure: expected student Id to be 4", studentId, actual.get(0).getStudent().getId());
    }

}