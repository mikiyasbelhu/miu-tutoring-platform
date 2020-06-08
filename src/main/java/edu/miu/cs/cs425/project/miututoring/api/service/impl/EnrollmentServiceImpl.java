package edu.miu.cs.cs425.project.miututoring.api.service.impl;

import edu.miu.cs.cs425.project.miututoring.api.model.Enrollment;
import edu.miu.cs.cs425.project.miututoring.api.repository.EnrollmentRepository;
import edu.miu.cs.cs425.project.miututoring.api.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
    EnrollmentRepository enrollmentRepository;

    @Autowired
    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }


    @Override
    public List<Enrollment> getAllEnrollment() {
        return enrollmentRepository.findAll();
    }

    @Override
    public Enrollment getEnrollmentById(Integer id) {
        return enrollmentRepository.findById(id).orElse(null);
    }

    @Override
    public Enrollment updateEnrollment(Enrollment updatedEnrollment, Integer enrollmentId) {
        return enrollmentRepository.findById(enrollmentId).
                map(enrollment -> {
                    enrollment.setRole(updatedEnrollment.getRole());
                    enrollment.setSection(updatedEnrollment.getSection());
                    enrollment.setTutorialGroup(updatedEnrollment.getTutorialGroup());
                    return enrollmentRepository.save(enrollment);
                }).orElseGet(()->enrollmentRepository.save(updatedEnrollment));
    }

    @Override
    public void deleteEnrollmentById(Integer enrollmentId) {
        enrollmentRepository.deleteById(enrollmentId);

    }

    @Override
    public Enrollment registerEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

}
