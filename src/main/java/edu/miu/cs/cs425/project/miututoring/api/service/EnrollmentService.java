package edu.miu.cs.cs425.project.miututoring.api.service;

import edu.miu.cs.cs425.project.miututoring.api.model.Enrollment;
import edu.miu.cs.cs425.project.miututoring.api.model.TutorialGroup;


import java.util.List;

public interface EnrollmentService {
    List<Enrollment>getAllEnrollment();
    Enrollment getEnrollmentById(Integer id);
    Enrollment saveEnrollment(Enrollment enrollment);
    Enrollment updateEnrollment(Enrollment updatedEnrollment, Integer enrollmentId);
    void deleteEnrollmentById(Integer enrollmentId);
    Enrollment registerEnrollment(Enrollment enrollment);
}
