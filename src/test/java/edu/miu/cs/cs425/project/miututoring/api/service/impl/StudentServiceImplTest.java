package edu.miu.cs.cs425.project.miututoring.api.service.impl;

import edu.miu.cs.cs425.project.miututoring.api.AbstractMiuTutoringComponentTest;
import edu.miu.cs.cs425.project.miututoring.api.model.Student;
import edu.miu.cs.cs425.project.miututoring.api.service.StudentService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
class StudentServiceImplTest extends AbstractMiuTutoringComponentTest {

    @Autowired
    StudentService studentService;


    @Before
    public void setUp(){
        logger.info("StudentServiceImplTest started");
    }

    @After
    public void tearDown(){logger.info("StudentServiceImplTest completed");}

    @Test
    void getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        Assert.assertEquals("Failure: expected size", 3,students.size());
    }

    @Test
    void testGetAllStudentsPaged() {
        Page<Student> students = studentService.getAllStudentsPaged(1,10,"",false);
        Assert.assertEquals("Failure: expected size", 3,students.getTotalElements());
    }

    @Test
    void testGetStudentById() {
        Long studentId = Long.valueOf(2);
        Student student = studentService.getStudentById(studentId);
        Assert.assertNotNull("Failure: expected student to be not null", student);
        Assert.assertEquals("Failure: expected studentId to match", studentId, student.getId());
    }

    @Test
    void testRegisterStudent() throws Exception {
        Student student = new Student("student5@miu.edu", "student", "000-61-0006", "John", "Kaleb", "Peters", 3.54, LocalDate.of(2019, 5, 21));
        Student savedStudent = studentService.registerStudent(student);
        Assert.assertNotNull("Failure: expected not null", savedStudent);
        Assert.assertNotNull("Failure: expected student userName to be not null",savedStudent.getUsername());
        Assert.assertEquals("Failure: expected student cgpa to match", (Double) 3.54, savedStudent.getCgpa());
        List<Student> students = studentService.getAllStudents();
        Assert.assertEquals("Failure: expected size",4,students.size());
        logger.info("Students list data: "+ Arrays.toString(students.toArray()));
    }

    @Test
    void testUpdateStudent() {
        Long studentId = Long.valueOf(2);
        Student student = studentService.getStudentById(studentId);
        student.setStudentNumber("61-3892");
        student.setFirstName("Abebe");
        student.setMiddleName("Chala");
        student.setLastName("Girmachew");
        student.setEnrollmentDate(LocalDate.of(2019, 5, 21));
        Student updatedStudent =  studentService.updateStudent(student,studentId);
        Assert.assertNotNull("Failure: expected updated student not to be null", updatedStudent);
        Assert.assertNotNull("Failure: expected updated studentUserName not to be null", updatedStudent.getUsername());
        Assert.assertEquals("Failure: expected student number to be the changed", "61-3892",student.getStudentNumber());
        Assert.assertEquals("Failure: expected first name to be the changed", "Abebe",student.getFirstName());
        Assert.assertEquals("Failure: expected middle name to be the changed", "Chala",student.getMiddleName());
        Assert.assertEquals("Failure: expected last name to be the changed", "Girmachew",student.getLastName());
        Assert.assertEquals("Failure: expected enrollment date to be the changed", LocalDate.of(2019, 5, 21),student.getEnrollmentDate());
        logger.info("Report Data: " + updatedStudent);
    }

    @Test
    void testDeleteStudentById() throws Exception {
        Student student = new Student("student6@miu.edu", "student", "000-61-0007", "John", "Kaleb", "Peters", 3.54, LocalDate.of(2019, 5, 21));
        Student savedStudent = studentService.registerStudent(student);
        Assert.assertNotNull("Failure: expected student to be not null", savedStudent);
        List<Student> students = studentService.getAllStudents();
        Assert.assertEquals("Failure: expected size", 4,students.size());
        studentService.deleteStudentById(student.getId());
        Student deletedStudent = studentService.getStudentById(student.getId());
        Assert.assertNull("Failure: expected deletedStudent to be null since is supposed to have been deleted", deletedStudent);
    }

    @Test
    void testSearchStudents() {
        Page<Student> students = studentService.searchStudents("Tigist",1,10,"",false);
        Assert.assertEquals("Failure: expected list to be not null", 1,students.getTotalElements());
    }

    @Test
    void testGetByUsername() {
        String username = "student@miu.edu";
        Student student = studentService.getByUsername(username);
        Assert.assertNotNull("Failure: expected student to be not null", student);
        Assert.assertEquals("Failure: expected studentId to match", username, student.getUsername());
    }
}