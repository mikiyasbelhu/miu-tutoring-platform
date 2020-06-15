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
class TutorRequestImplTest extends AbstractMiuTutoringComponentTest {

    @Autowired
    TutorRequestService tutorRequestService;
    @Autowired
    SectionService sectionService;
    @Autowired
    EnrollmentService enrollmentService;
    @Autowired
    StudentService studentService;
    @Autowired
    FacultyService facultyService;
    @Autowired
    CourseService courseService;
    @Autowired
    TutorialGroupService tutorialGroupService;


    @BeforeEach
    void setUp() {
        logger.info("TutorRequestServiceImplTest started");
    }

    @AfterEach
    void tearDown() {
        logger.info("TutorRequestServiceImplTest completed");
    }

    @Test
    void listTutorRequests() {
        List<TutorRequest> tutorRequests = tutorRequestService.listTutorRequests();
        Assert.assertNotNull("Failure: Expected tutorial request list not to be null", tutorRequests);
        Assert.assertEquals("Failure: Expected number of Tutor Requests to be equal", 2, tutorRequests.size());
        logger.info("Reports list data: " + Arrays.toString(tutorRequests.toArray()));
    }

    @Test
    void getTutorRequestById() {
        Integer tutorRequestId = 1;
        TutorRequest actual = tutorRequestService.getTutorRequestById(1);
        Assert.assertNotNull("Failure: expected tutorial request to not be null", actual);
        Assert.assertEquals("Failure: expected tutorial request Id to be 1", tutorRequestId, actual.getRequestId());
        logger.info("Report: " + actual);
    }

    @Test
    void getTutorRequestByInvalidId(){
        Integer requestId = Integer.MAX_VALUE;
        TutorRequest actual = tutorRequestService.getTutorRequestById(requestId);
        Assert.assertNull("Failure: expected tutorial request to be not null", actual);
    }

    @Test
    void deleteTutorRequestById() {
        TutorRequest actual = tutorRequestService.getTutorRequestById(2);
        Assert.assertNotNull("Failure: expected tutorial request to not be null", actual);
        tutorRequestService.deleteTutorRequestById(2);
        List<TutorRequest> tutorRequests = tutorRequestService.listTutorRequests();
        Assert.assertEquals("Failure: expected list to be null", 1, tutorRequests.size());
    }

    @Test
    void saveTutorRequest() throws Exception {
        Student student3 = new Student("aaaa@hi2.in", "mine", "000-61-0004", "Tigist", "Gutema", "Kefyalew", 3.84, LocalDate.of(2019, 5, 21));
        studentService.registerStudent(student3);
        Faculty faculty2 = new Faculty("bbbbb@miu.edu", "yours", "Michael", "", "Zijlstra", "Computer Science CS420");
        facultyService.registerFaculty(faculty2);
        Course course1 = new Course("CS420", "MPP", 4);
        courseService.saveCourse(course1);
        Section section2 = new Section("CS420-2020-06-03", "Library 210", "2020-08", course1, faculty2);
        sectionService.saveSection(section2);
        TutorialGroup tutorialGroup1 = new TutorialGroup("CS425", section2);
        tutorialGroupService.registerTutorialGroup(tutorialGroup1);
        Enrollment enrollment1 = new Enrollment(student3, section2);
        enrollmentService.saveEnrollment(enrollment1);

        TutorRequest tutorRequest = new TutorRequest(section2, enrollment1, "I have 10 years of experience");
        TutorRequest actual = tutorRequestService.saveTutorRequest(tutorRequest);
        Assert.assertNotNull("Failure: expected tutorial request not to be null", actual);
        List<TutorRequest> tutorRequests = tutorRequestService.listTutorRequests();
        Assert.assertEquals("Failure: expected 3 tutorial requests", 3, tutorRequests.size());
        logger.info("Tutorial requests list Data: " + Arrays.toString(tutorRequests.toArray()));
    }

    @Test
    void updateTutorRequest() throws Exception {
        Student student3 = new Student("aaaa@hi2.in", "mine", "000-61-0004", "Tigist", "Gutema", "Kefyalew", 3.84, LocalDate.of(2019, 5, 21));
        studentService.registerStudent(student3);
        Faculty faculty2 = new Faculty("bbbbb@miu.edu", "yours", "Michael", "", "Zijlstra", "Computer Science CS420");
        facultyService.registerFaculty(faculty2);
        Course course1 = new Course("CS420", "MPP", 4);
        courseService.saveCourse(course1);
        Section section2 = new Section("CS420-2020-06-03", "Library 210", "2020-08", course1, faculty2);
        sectionService.saveSection(section2);
        TutorialGroup tutorialGroup1 = new TutorialGroup("CS425", section2);
        tutorialGroupService.registerTutorialGroup(tutorialGroup1);
        Enrollment enrollment1 = new Enrollment(student3, section2);
        enrollmentService.saveEnrollment(enrollment1);
        TutorRequest updater = new TutorRequest(section2, enrollment1, "I have 10 years of experience");

        TutorRequest updated = tutorRequestService.updateTutorRequest(updater,2);
        Assert.assertNotNull("Failure: expected tutor request not to be null", updated);
        Assert.assertNotNull("Failure: expected tutor request Id not to be null", updated.getRequestId());
        Assert.assertEquals("Failure: expected section Id to be the same", section2.getSectionId(), updated.getSection().getSectionId());
        Assert.assertEquals("Failure: expected enrollment Id to be the same", enrollment1.getEnrollmentId(), updated.getEnrollment().getEnrollmentId());
        logger.info("Tutorial Request data : " + updated);
    }

    @Test
    void acceptTutorRequest() throws Exception {
        Student student3 = new Student("aaaa@hi2.in", "mine", "000-61-0004", "Tigist", "Gutema", "Kefyalew", 3.84, LocalDate.of(2019, 5, 21));
        studentService.registerStudent(student3);
        Faculty faculty2 = new Faculty("bbbbb@miu.edu", "yours", "Michael", "", "Zijlstra", "Computer Science CS420");
        facultyService.registerFaculty(faculty2);
        Course course1 = new Course("CS420", "MPP", 4);
        courseService.saveCourse(course1);
        Section section2 = new Section("CS420-2020-06-03", "Library 210", "2020-08", course1, faculty2);
        sectionService.saveSection(section2);
        TutorialGroup tutorialGroup1 = new TutorialGroup("CS425", section2);
        tutorialGroupService.registerTutorialGroup(tutorialGroup1);
        TutorRequest accepted = tutorRequestService.getTutorRequestById(1);
        accepted.setStatus(TutorRequest.Status.ACCEPTED);
        TutorRequest actual = tutorRequestService.acceptTutorRequest(1,tutorialGroup1);
        Assert.assertNotNull("Failure: expected tutor request not to be null", actual);
        Assert.assertEquals("Failure: expected tutor request status to be the same", accepted.getStatus(), actual.getStatus());
        logger.info("Tutorial Request Data: " + actual);
    }

    @Test
    void denyTutorRequest() {
        TutorRequest rejected = tutorRequestService.getTutorRequestById(1);
        rejected.setStatus(TutorRequest.Status.REJECTED);
        TutorRequest actual = tutorRequestService.denyTutorRequest(1);
        Assert.assertNotNull("Failure: expected tutor request to not be null", actual);
        Assert.assertEquals("Failure: expected tutor request status to be the same",rejected.getStatus(), actual.getStatus());
        logger.info("Tutorial Request data: " + actual);
    }
}