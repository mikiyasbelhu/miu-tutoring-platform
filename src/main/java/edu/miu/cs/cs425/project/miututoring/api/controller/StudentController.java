package edu.miu.cs.cs425.project.miututoring.api.controller;

import edu.miu.cs.cs425.project.miututoring.api.model.Student;
import edu.miu.cs.cs425.project.miututoring.api.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;

@RestController
@RequestMapping(value = "/api/v1/student", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentController {

    StudentService studentService;

    @Autowired
    StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping(value="/list")
    public Page<Student> listStudents(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer itemsPerPage,
                                      @RequestParam(defaultValue = "") String sortBy,@RequestParam(defaultValue = "false") Boolean sortDesc){
        return studentService.getAllStudentsPaged(page,itemsPerPage,sortBy,sortDesc);
    }

    @GetMapping(value = {"/get/{studentId}"})
    public Student getStudent(@PathVariable Long studentId) {
        return studentService.getStudentById(studentId);
    }

    @PostMapping(value = "/register")
    public Student registerStudent(@Valid @RequestBody Student student) throws Exception {
        student.setRoles(new ArrayList<>(Arrays.asList("ROLE_STUDENT")));
        return studentService.registerStudent(student);
    }

    @PutMapping(value = "/edit/{studentId}")
    public Student updateStudent(@Valid @RequestBody Student updatedStudent, @PathVariable Long studentId){
        return studentService.updateStudent(updatedStudent,studentId);
    }

    @DeleteMapping(value = {"/delete/{studentId}"})
    public void deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudentById(studentId);
    }

    @GetMapping(value = {"/search"})
    public Page<Student> searchStudent(@RequestParam String searchQuery, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer itemsPerPage,
                                       @RequestParam(defaultValue = "") String sortBy,@RequestParam(defaultValue = "false") Boolean sortDesc) {
        return studentService.searchStudents(searchQuery,page,itemsPerPage,sortBy,sortDesc);
    }

}
