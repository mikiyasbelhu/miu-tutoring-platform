package edu.miu.cs.cs425.project.miututoring.api;

import edu.miu.cs.cs425.project.miututoring.api.model.*;
import edu.miu.cs.cs425.project.miututoring.api.service.*;
import edu.miu.cs.cs425.project.miututoring.api.repository.*;
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
                           PasswordEncoder passwordEncoder, EnrollmentService enrollmentService){
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

        // Admin
        List<User> usersList = new ArrayList<User>(Arrays.asList(
                new User("rmhbcd@hi2.in","admin","George","","Cannon", new ArrayList<>(Arrays.asList("ROLE_ADMIN","ROLE_FACULTY")))
        ));
        saveUsers(usersList);

        // Student
        Student student1 = new Student("student@miu.edu","student","000-61-0001", "Anna", "Lynn", "Smith", 3.45, LocalDate.of(2019,5,24));
        Student student2 = new Student("tutor@miu.edu","tutor123","000-61-0002", "John", "Trester", "George", 3.24, LocalDate.of(2019,5,21));
        saveStudent(student1);
        saveStudent(student2);

        // Faculty
        Faculty faculty1 = new Faculty("faculty@miu.edu","faculty", "Obinna", "A", "Kalu","Software Engineeing CS425");
        saveFaculty(faculty1);

        // Course        
        Course course = new Course("CS425", "Software Engineering", 4);
        saveCourse(course);

        // Section
        Section section = new Section("CS425-2020-06-01", "Library 109", "2020-06", course, faculty1);
        saveSection(section);

        // Tutorial Group
        TutorialGroup tutorialGroup1 = new TutorialGroup("CS425",section);
        TutorialGroup tutorialGroup2 = new TutorialGroup("CS575",section);
        saveTutorialGroup(tutorialGroup1);
        saveTutorialGroup(tutorialGroup2);

        // Enrollment
        Enrollment.RoleType tutor= Enrollment.RoleType.TUTOR;
        Enrollment enrollment = new Enrollment(student1, tutor, section, tutorialGroup1);
        saveEnrollment(enrollment);

//        List<Enrollment> list = new ArrayList<>();
//        list.add(enrollment);
//        tutorialGroup1.setEnrollments(list);
//        saveTutorialGroup(tutorialGroup1);

    }

    public void saveStudent(Student student){
        studentService.registerStudent(student);
    }

    public void saveFaculty(Faculty faculty){
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
