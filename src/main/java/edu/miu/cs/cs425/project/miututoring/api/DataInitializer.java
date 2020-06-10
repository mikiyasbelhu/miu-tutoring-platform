package edu.miu.cs.cs425.project.miututoring.api;

import edu.miu.cs.cs425.project.miututoring.api.model.Student;
import edu.miu.cs.cs425.project.miututoring.api.model.TutorialGroup;
import edu.miu.cs.cs425.project.miututoring.api.model.User;
import edu.miu.cs.cs425.project.miututoring.api.service.StudentService;
import edu.miu.cs.cs425.project.miututoring.api.repository.TutorialGroupRepository;
import edu.miu.cs.cs425.project.miututoring.api.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    StudentService studentService;

    @Autowired
    TutorialGroupRepository tutorialGroupRepository;

    @Autowired
    MyUserDetailsService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Student student1 = new Student("student@miu.edu","student","000-61-0001", "Anna", "Lynn", "Smith", 3.45, LocalDate.of(2019,5,24));
        Student student2 = new Student("tutor@miu.edu","tutor123","000-61-0002", "John", "Trester", "George", 3.24, LocalDate.of(2019,5,21));
        saveStudent(student1);
        saveStudent(student2);
        TutorialGroup tutorialGroup1 = new TutorialGroup(1L,"CS425");
        TutorialGroup tutorialGroup2 = new TutorialGroup(2L,"CS575");
        saveTutorialGroup(tutorialGroup1);
        saveTutorialGroup(tutorialGroup2);
        List<User> usersList = new ArrayList<User>(Arrays.asList(
                new User("admin@miu.edu",this.passwordEncoder.encode("admin"), new ArrayList<>(Arrays.asList("ROLE_ADMIN","ROLE_FACULTY"))),
                new User("faculty@miu.edu",this.passwordEncoder.encode("faculty"), new ArrayList<>(Arrays.asList("ROLE_FACULTY")))
        ));
        saveUsers(usersList);
    }

    public void saveStudent(Student student){
        studentService.registerStudent(student);
    }
    public void saveTutorialGroup(TutorialGroup tutorialGroup){
        tutorialGroupRepository.save(tutorialGroup);
    }
    public void saveUsers(List<User> users){
        userService.saveUsers(users);
    }

}
