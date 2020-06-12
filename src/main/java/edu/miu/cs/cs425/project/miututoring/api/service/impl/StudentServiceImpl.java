package edu.miu.cs.cs425.project.miututoring.api.service.impl;

import edu.miu.cs.cs425.project.miututoring.api.model.Student;
import edu.miu.cs.cs425.project.miututoring.api.repository.StudentRepository;
import edu.miu.cs.cs425.project.miututoring.api.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    StudentRepository studentRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    StudentServiceImpl(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Page<Student> getAllStudentsPaged(int pageNo,Integer pageSize, String sortBy, Boolean sortDesc) {
        return studentRepository.findAll(PageRequest.of(pageNo, pageSize == -1 ? Integer.MAX_VALUE :pageSize, Sort.by(sortDesc ? Sort.Direction.DESC :Sort.Direction.ASC ,sortBy)));
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student registerStudent(Student student) {
        if(!studentRepository.findByUsername(student.getUsername()).isPresent()){
            student.setPassword(this.passwordEncoder.encode(student.getPassword()));
            return studentRepository.save(student);
        }
        return null;
    }

    @Override
    public Student updateStudent(Student updatedStudent, Long studentId) {
        return studentRepository.findById(studentId)
                .map(student -> {
                    student.setStudentNumber(updatedStudent.getStudentNumber());
                    student.setFirstName(updatedStudent.getFirstName());
                    student.setMiddleName(updatedStudent.getMiddleName());
                    student.setLastName(updatedStudent.getLastName());
                    student.setCgpa(updatedStudent.getCgpa());
                    student.setEnrollmentDate(updatedStudent.getEnrollmentDate());
                    return studentRepository.save(student);
                })
                .orElseGet(() -> studentRepository.save(updatedStudent));
    }

    @Override
    public void deleteStudentById(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public Page<Student> searchStudents(String searchQuery, int pageNo,Integer pageSize,String sortBy, Boolean sortDesc) {
        return studentRepository.findAllByStudentNumberContainingOrFirstNameContainingOrMiddleNameContainingOrLastNameContainingOrderByFirstName(searchQuery, searchQuery, searchQuery, searchQuery, PageRequest.of(pageNo, pageSize, Sort.by(sortDesc ? Sort.Direction.DESC :Sort.Direction.ASC ,sortBy)));
    }

}
