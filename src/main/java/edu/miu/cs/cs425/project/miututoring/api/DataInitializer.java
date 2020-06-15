package edu.miu.cs.cs425.project.miututoring.api;

import edu.miu.cs.cs425.project.miututoring.api.model.*;
import edu.miu.cs.cs425.project.miututoring.api.service.*;
import edu.miu.cs.cs425.project.miututoring.api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    FacultyService facultyService;
    StudentService studentService;
    MyUserDetailsService userService;
    CourseService courseService;
    SectionService sectionService;
    TutorialGroupRepository tutorialGroupRepository;
    EnrollmentService enrollmentService;
    PasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializer(FacultyService facultyService, StudentService studentService,
                           MyUserDetailsService myUserDetailsService, CourseService courseService,
                           SectionService sectionService, TutorialGroupRepository tutorialGroupRepository,
                           @Lazy PasswordEncoder passwordEncoder, EnrollmentService enrollmentService){
        this.studentService = studentService;
        this.facultyService = facultyService;
        this.userService = myUserDetailsService;
        this.courseService = courseService;
        this.sectionService = sectionService;
        this.tutorialGroupRepository = tutorialGroupRepository;
        this.passwordEncoder = passwordEncoder;
        this.enrollmentService = enrollmentService;
    }

    @Override
    public void run(String... args) throws Exception {

        Boolean isInitialized = false;

        // Admin
        List<User> usersList = new ArrayList<User>(Arrays.asList(
                new User("admin@miu.edu","admin","George","","Cannon", new ArrayList<>(Arrays.asList("ROLE_ADMIN")))
        ));
        saveUsers(usersList);

        // Student
        Student student1 = new Student("student@miu.edu","student","000-61-0001", "Tesfaye", "Lemma", "Girum", 3.45, LocalDate.of(2019,5,24));
        Student student2 = new Student("tutor@miu.edu","tutor123","000-61-0002", "Haile", "Teshale", "Alemu", 3.24, LocalDate.of(2019,5,21));
        Student student3 = new Student("rmhbcd@hi2.in","student","000-61-0003", "Tigist", "Gutema", "Kefyalew", 3.84, LocalDate.of(2019,5,21));
        
        try {
            saveStudent(student1);
            saveStudent(student2);
            saveStudent(student3);
        }
        catch(Exception ex){
            student1 = studentService.getByUsername("student@miu.edu");
            System.out.println("Data already initialized");
            isInitialized = true;
        }

        if(!isInitialized){

            // Faculty
            Faculty faculty1 = new Faculty("faculty@miu.edu","faculty", "John", "A", "Peterson","Software Engineeing CS425");
            try {
                saveFaculty(faculty1);
            }
            catch(Exception ex){
                faculty1 = facultyService.getByUsername("faculty@miu.edu");
            }

            // Course
            Course course = new Course("CS425", "Software Engineering", 4);
            saveCourse(course);

            // Section
            Section section = new Section("CS425-2020-06-01", "Library 109", "2020-06", course);
            section.setFaculty(faculty1);
            saveSection(section);

            // Tutorial Group
            TutorialGroup tutorialGroup1 = new TutorialGroup("Group 1",section);
            TutorialGroup tutorialGroup2 = new TutorialGroup("Group 2",section);
            saveTutorialGroup(tutorialGroup1);
            saveTutorialGroup(tutorialGroup2);

            // Enrollment
            Enrollment enrollment = new Enrollment(student1, section);
            enrollment.setTutorialGroup(tutorialGroup1);
            saveEnrollment(enrollment);

            List<Enrollment> list = new ArrayList<>();
            list.add(enrollment);
            tutorialGroup1.setEnrollments(list);
            saveTutorialGroup(tutorialGroup1);
        }
    }

    public void saveStudent(Student student) throws Exception {
        studentService.registerStudent(student);
    }

    public void saveFaculty(Faculty faculty) throws Exception {
        facultyService.registerFaculty(faculty);
    }

    public void saveUsers(List<User> users){
        userService.saveUsers(users);
    }

    public void saveCourse(Course course){
        courseService.saveCourse(course);
    }

    public void saveSection(Section section){
        sectionService.registerSection(section);
    }

    public void saveTutorialGroup(TutorialGroup tutorialGroup){
        tutorialGroupRepository.save(tutorialGroup);
    }

    public void saveEnrollment(Enrollment enrollment){
        enrollmentService.saveEnrollment(enrollment);
    }

}
