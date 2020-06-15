package edu.miu.cs.cs425.project.miututoring.api.service;

import edu.miu.cs.cs425.project.miututoring.api.model.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Page<Student> getAllStudentsPaged(int pageNo,Integer pageSize, String sortBy, Boolean sortDesc);
    Student getStudentById(Long id);
    Student registerStudent(Student student) throws Exception;
    Student updateStudent(Student updatedStudent, Long studentId);
    void deleteStudentById(Long studentId);
    Page<Student> searchStudents(String searchQuery, int pageNo,Integer pageSize,String sortBy, Boolean sortDesc);
    Student getByUsername(String username);
}
