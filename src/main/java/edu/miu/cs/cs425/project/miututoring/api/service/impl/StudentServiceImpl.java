package edu.miu.cs.cs425.project.miututoring.api.service.impl;

import edu.miu.cs.cs425.project.miututoring.api.model.Student;
import edu.miu.cs.cs425.project.miututoring.api.repository.StudentRepository;
import edu.miu.cs.cs425.project.miututoring.api.service.NotificationService;
import edu.miu.cs.cs425.project.miututoring.api.service.StudentService;
import edu.miu.cs.cs425.project.miututoring.api.util.EmailGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Value("${spring.mail.username}")
    private String username;

    StudentRepository studentRepository;
    PasswordEncoder passwordEncoder;
    NotificationService notificationService;

    @Autowired
    StudentServiceImpl(StudentRepository studentRepository, PasswordEncoder passwordEncoder, NotificationService notificationService){
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
        this.notificationService = notificationService;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Page<Student> getAllStudentsPaged(int pageNo,Integer pageSize, String sortBy, Boolean sortDesc) {
        return studentRepository.findAll(PageRequest.of(pageNo, pageSize == -1 ? Integer.MAX_VALUE :pageSize, sortBy.equals("") ? Sort.unsorted() : Sort.by(sortDesc ? Sort.Direction.DESC :Sort.Direction.ASC ,sortBy)));
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student registerStudent(Student student) {
        if(!studentRepository.findByUsername(student.getUsername()).isPresent()){
            String plainPasssword = student.getPassword();
            student.setPassword(this.passwordEncoder.encode(plainPasssword));

            Student savedStudent = studentRepository.save(student);
            String message =  EmailGenerator.generateWelcomeMessage(savedStudent.getFirstName(),savedStudent.getUsername(),plainPasssword);
            String body = EmailGenerator.generateEmail(message);
//            try {
//                notificationService.sendNotification(username,student.getUsername(),
//                        body, "MIU Tutoring registration");
//            } catch (MessagingException e) {
//                System.out.println("Unable to send email");
//            }
            return savedStudent;
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
