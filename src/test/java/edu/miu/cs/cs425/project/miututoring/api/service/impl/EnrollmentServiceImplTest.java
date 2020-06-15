package edu.miu.cs.cs425.project.miututoring.api.service.impl;

import edu.miu.cs.cs425.project.miututoring.api.AbstractMiuTutoringComponentTest;
import edu.miu.cs.cs425.project.miututoring.api.model.*;
import edu.miu.cs.cs425.project.miututoring.api.service.*;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@Transactional
class EnrollmentServiceImplTest extends AbstractMiuTutoringComponentTest {
    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private SectionService sectionService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private FacultyService facultyService;
    @Autowired
    private StudentService studentService;


    @BeforeEach
    void setUp() {logger.info("EnrollmentServiceImplTest started");
    }

    @AfterEach
    void tearDown() {logger.info("EnrollmentServiceImplTest completed");
    }

    @Test
    void getAllEnrollment() {
        List<Enrollment> enrollment=enrollmentService.getAllEnrollment();
        Assert.assertNotNull("Failure:expected not null",enrollment);
        Assert.assertEquals("Failure:expected size",1,enrollment.size());
        logger.info("Enrollment list data"+ Arrays.toString(enrollment.toArray()));
    }

    @Test
    void getEnrollmentById() {
        Integer enrollmentId=new Integer(1);
        Enrollment enrollment=enrollmentService.getEnrollmentById(enrollmentId);
        Assert.assertNotNull("Failure:expected not null",enrollment);
        Assert.assertEquals("Failure:expected enrollmentId match",enrollmentId,enrollment.getEnrollmentId());
        logger.info("Enrollment data",enrollment);
    }

    @Test
    void saveEnrollment() throws Exception {

        Student student10 = new Student("student3@miu.edu","student-me","000-61-1111", "Girma", "Sisay", "B.", 3.6, LocalDate.of(2020,1,10));
        Course course2 = new Course("CS525", "EA", 6);
        Faculty faculty2 = new Faculty("abcd@miu.edu","faculty", "Obk", "A",
                "Luka","Engineering ");
        Student savedStudent=studentService.registerStudent(student10);
        Course savedCourse = courseService.saveCourse(course2);
        Faculty savedFaculty = facultyService.registerFaculty(faculty2);
        Section sectionAB = new Section("AB", "Class AB", "2020-07", savedCourse, savedFaculty);

        Section savedSection=sectionService.saveSection(sectionAB);

        Enrollment enrollment=new Enrollment(savedStudent,savedSection);

        Enrollment savedEnrollment=enrollmentService.registerEnrollment(enrollment);

        Assert.assertNotNull("Failure: expected not null", savedEnrollment);
        Assert.assertNotNull("Failure: expected sectionId to be not null", savedEnrollment.getEnrollmentId());

        List<Enrollment>enrollments=enrollmentService.getAllEnrollment();
        Assert.assertEquals("Failure:expected to be equal",2,enrollments.size());

        Assert.assertEquals("Failure: expected enrollment section name match",savedSection.getSectionName(),savedEnrollment.getSection().getSectionName());
        Assert.assertEquals("Failure: expected enrollment student number match", savedStudent.getStudentNumber(),savedEnrollment.getStudent().getStudentNumber());


    }

    @Test
    void updateEnrollment() throws Exception {
        Integer enrollmentId=new Integer(1);
        Enrollment newEnrollment=enrollmentService.getEnrollmentById(enrollmentId);

        Course course2 = new Course("CS525", "EA", 6);
        Faculty faculty2 = new Faculty("abcd@miu.edu","faculty", "Obk", "A",
                "Luka","Engineering ");
        Course savedCourse = courseService.saveCourse(course2);
        Faculty savedFaculty = facultyService.registerFaculty(faculty2);
        Section sectionAB = new Section("AB", "Class AB", "2020-07", savedCourse, savedFaculty);

        Section savedSection=sectionService.saveSection(sectionAB);
        newEnrollment.setSection(savedSection);


        Enrollment updated=enrollmentService.updateEnrollment(newEnrollment,1);
        System.out.println(updated);


        Assert.assertNotNull("Failure: expected not null", updated);
        Assert.assertNotNull("Failure:expected not null",updated.getEnrollmentId());
        Assert.assertEquals("Failure:expected sectionName of enrollment to match",newEnrollment.getSection().getSectionName(),updated.getSection().getSectionName());
        Assert.assertEquals("Failure:expected enrolled student name to match",newEnrollment.getStudent().getFirstName(),updated.getStudent().getFirstName());
        logger.info("Updated data" + updated);

    }

    @Test
    void deleteEnrollmentById() {
        Integer enrollmentId = new Integer(1);
        Enrollment enrollment = enrollmentService.getEnrollmentById(enrollmentId);
        Assert.assertNotNull("Failure: expected section to be not null", enrollment);
        enrollmentService.deleteEnrollmentById(enrollmentId);
        List<Enrollment> enrollments = enrollmentService.getAllEnrollment();
        Assert.assertEquals("Failure: expected size", 0, enrollments.size());
        Enrollment deletedEnrollment = enrollmentService.getEnrollmentById(enrollmentId);
        Assert.assertNull("Failure: expected deleted Section to be null since is supposed to have been deleted", deletedEnrollment);
    }


}