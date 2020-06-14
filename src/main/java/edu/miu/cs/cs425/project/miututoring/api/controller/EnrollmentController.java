package edu.miu.cs.cs425.project.miututoring.api.controller;

import edu.miu.cs.cs425.project.miututoring.api.model.Enrollment;
import edu.miu.cs.cs425.project.miututoring.api.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/api/v1/enrollment")
public class EnrollmentController {

    EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping(value="/list")
    public List<Enrollment>listOfEnrollment(){
        return enrollmentService.getAllEnrollment();
    }

    @GetMapping(value="/get/{enrollmentId}")
    public Enrollment getEnrollment(@PathVariable Integer enrollmentId){
        return enrollmentService.getEnrollmentById(enrollmentId);
    }

    @PutMapping(value="/edit/{enrollmentId}")
    public Enrollment updateEnrollment(@Valid @RequestBody Enrollment enrollment,@PathVariable Integer enrollmentId){
        return enrollmentService.updateEnrollment(enrollment,enrollmentId);
    }

    @PostMapping(value="/register")
    public Enrollment registerEnrollment(@Valid @RequestBody Enrollment enrollment){
        return enrollmentService.registerEnrollment(enrollment);
    }

    @DeleteMapping(value="/delete/{enrollmentId}")
    public void deleteEnrollment(@PathVariable Integer enrollmentId){
        enrollmentService.deleteEnrollmentById(enrollmentId);
    }

    @GetMapping(value="/getbystudent/{studentId}")
    public List<Enrollment> getEnrollmentByStudent(@PathVariable Long studentId) {
        return enrollmentService.getEnrollmentByStudent(studentId);
    }
}
