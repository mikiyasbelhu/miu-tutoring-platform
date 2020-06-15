package edu.miu.cs.cs425.project.miututoring.api.service.impl;

import edu.miu.cs.cs425.project.miututoring.api.model.Faculty;
import edu.miu.cs.cs425.project.miututoring.api.repository.FacultyRepository;
import edu.miu.cs.cs425.project.miututoring.api.service.FacultyService;
import edu.miu.cs.cs425.project.miututoring.api.service.NotificationService;
import edu.miu.cs.cs425.project.miututoring.api.util.EmailGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {

    @Value("${spring.mail.username}")
    private String username;

    FacultyRepository facultyRepository;
    PasswordEncoder passwordEncoder;
    NotificationService notificationService;

    @Autowired
    public FacultyServiceImpl(FacultyRepository facultyRepository, @Lazy PasswordEncoder passwordEncoder, NotificationService notificationService ){
        this.facultyRepository = facultyRepository;
        this.passwordEncoder = passwordEncoder;
        this.notificationService = notificationService;
    }

    @Override
    public List<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }

    @Override
    public Page<Faculty> getAllFacultyPaged(int pageNo, Integer pageSize, String sortBy, Boolean sortDesc) {
        return facultyRepository.findAll(PageRequest.of(pageNo, pageSize == -1 ? Integer.MAX_VALUE :pageSize, !sortBy.equals("") ? Sort.by(sortDesc?Sort.Direction.DESC :Sort.Direction.ASC, sortBy): Sort.unsorted()));
    }

    @Override
    public Faculty getFacultyById(Long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    @Override
    public Faculty registerFaculty(Faculty faculty) throws Exception {
         if(!facultyRepository.findByUsername(faculty.getUsername()).isPresent()){
             String plainPasssword = faculty.getPassword();
             faculty.setPassword(this.passwordEncoder.encode(plainPasssword));

             Faculty savedFaculty = facultyRepository.save(faculty);
             String message =  EmailGenerator.generateWelcomeMessage(savedFaculty.getFirstName(),savedFaculty.getUsername(),plainPasssword);
             String body = EmailGenerator.generateEmail(message);
             try {
                 notificationService.sendNotification(username,faculty.getUsername(),
                         body, "MIU Tutoring registration");
             } catch (MessagingException e) {
                 System.out.println("Unable to send email");
             }
             return savedFaculty;
        }
         else{
             throw new Exception("Username " + username + " is already taken");
         }
    }

    @Override
    public Faculty updateFaculty(Faculty updateFaculty, Long facultyId) {
        return facultyRepository.findById(facultyId)
                .map(faculty -> {
                    faculty.setDepartment(updateFaculty.getDepartment());
                    faculty.setFirstName(updateFaculty.getFirstName());
                    faculty.setMiddleName(updateFaculty.getMiddleName());
                    faculty.setLastName(updateFaculty.getLastName());
                    return facultyRepository.save(faculty);
                })
                .orElseGet(() -> facultyRepository.save(updateFaculty));
    }

    @Override
    public void deleteFacultyById(Long facultyId) {
        facultyRepository.deleteById(facultyId);

    }

    @Override
    public Page<Faculty> searchFaculty(String searchQuery, int pageNo, Integer pageSize, String sortBy, Boolean sortDesc) {
        return facultyRepository.findAllByDepartmentContainingOrFirstNameContainingOrMiddleNameContainingOrLastNameContainingOrderByFirstName(searchQuery, searchQuery, searchQuery, searchQuery,PageRequest.of(pageNo,pageSize,Sort.by(sortDesc?Sort.Direction.DESC : Sort.Direction.ASC, sortBy)));
    }

    @Override
    public Faculty getByUsername(String username) {
        return facultyRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found"));
    }
}
