//package edu.miu.cs.cs425.project.miututoring.api;
//
//import edu.miu.cs.cs425.project.miututoring.api.model.*;
//import edu.miu.cs.cs425.project.miututoring.api.service.*;
//import edu.miu.cs.cs425.project.miututoring.api.repository.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//@Component
//public class DataInitializer implements CommandLineRunner {
//
//    FacultyService facultyService;
//    StudentService studentService;
//    MyUserDetailsService userService;
//    CourseService courseService;
//    SectionService sectionService;
//    TutorialGroupRepository tutorialGroupRepository;
//    EnrollmentService enrollmentService;
//    PasswordEncoder passwordEncoder;
//    TutorRequestService tutorRequestService;
//
//    @Autowired
//    public DataInitializer(FacultyService facultyService, StudentService studentService,
//                           MyUserDetailsService myUserDetailsService, CourseService courseService,
//                           SectionService sectionService, TutorialGroupRepository tutorialGroupRepository,
//                           PasswordEncoder passwordEncoder, EnrollmentService enrollmentService, TutorRequestService tutorRequestService){
//        this.studentService = studentService;
//        this.facultyService = facultyService;
//        this.userService = myUserDetailsService;
//        this.courseService = courseService;
//        this.sectionService = sectionService;
//        this.tutorialGroupRepository = tutorialGroupRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.enrollmentService = enrollmentService;
//        this.tutorRequestService = tutorRequestService;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        // Admin
//        List<User> usersList = new ArrayList<User>(Arrays.asList(
//                new User("admin@miu.edu","admin","George","","Cannon", new ArrayList<>(Arrays.asList("ROLE_ADMIN","ROLE_FACULTY")))
//        ));
//        saveUsers(usersList);
//
//        // Student
//        Student student1 = new Student("student@miu.edu","student","000-61-0001", "Tesfaye", "Lemma", "Girum", 3.45, LocalDate.of(2019,5,24));
//        Student student2 = new Student("tutor@miu.edu","tutor123","000-61-0002", "Haile", "Teshale", "Alemu", 3.24, LocalDate.of(2019,5,21));
//        Student student3 = new Student("rmhbcd@hi2.in","student","000-61-0003", "Tigist", "Gutema", "Kefyalew", 3.84, LocalDate.of(2019,5,21));
//        saveStudent(student1);
//        saveStudent(student2);
//        saveStudent(student3);
//
//        // Faculty
//        Faculty faculty1 = new Faculty("faculty@miu.edu","faculty", "Obinna", "A", "Kalu","Software Engineering CS425");
//        Faculty faculty2 = new Faculty("faculty1@miu.edu","password1", "Michael", "", "Zijlstra", "Computer Science CS420");
//        saveFaculty(faculty1);
//        saveFaculty(faculty2);
//
//        // Course
//        Course course = new Course("CS425", "Software Engineering", 4);
//        Course course1 = new Course("CS420", "MPP", 4);
//        saveCourse(course);
//        saveCourse(course1);
//
//        // Section
//        Section section = new Section("CS425-2020-06-01", "Library 109", "2020-06", course, faculty1);
//        Section section2 = new Section("CS420-2020-06-02", "Library 110", "2020-07", course1, faculty2);
//        saveSection(section);
//        saveSection(section2);
//
//        // Tutorial Group
//        TutorialGroup tutorialGroup1 = new TutorialGroup("CS425",section);
//        TutorialGroup tutorialGroup2 = new TutorialGroup("CS575",section);
//        TutorialGroup tutorialGroup3 = new TutorialGroup("CS420",section2);
//        saveTutorialGroup(tutorialGroup1);
//        saveTutorialGroup(tutorialGroup2);
//        saveTutorialGroup(tutorialGroup3);
//
//        // Enrollment
//        Enrollment enrollment = new Enrollment(student1, section);
//        Enrollment enrollment1 = new Enrollment(student2, section2);
//        enrollment1.setTutorialGroup(tutorialGroup3);
//        enrollment.setTutorialGroup(tutorialGroup1);
//        saveEnrollment(enrollment);
//        saveEnrollment(enrollment1);
//
//        List<Enrollment> list = new ArrayList<>();
//        list.add(enrollment);
//        list.add(enrollment1);
//        tutorialGroup1.setEnrollments(list);
//        saveTutorialGroup(tutorialGroup1);
//
//
//        List<Enrollment> list1 = new ArrayList<>();
//        list1.add(enrollment1);
//        tutorialGroup3.setEnrollments(list1);
//        saveTutorialGroup(tutorialGroup3);
//
//        //tutor request
//        TutorRequest request1 = new TutorRequest(section, enrollment, "I am well experienced");
//        saveTutorRequest(request1);
//
//        TutorRequest request2 = new TutorRequest(section2,enrollment1,"I feel like i can be a good tutor for this course");
//        saveTutorRequest(request2);
//    }
//
//    public void saveStudent(Student student){
//        studentService.registerStudent(student);
//    }
//
//    public void saveFaculty(Faculty faculty){
//        facultyService.registerFaculty(faculty);
//    }
//
//    public void saveUsers(List<User> users){
//        userService.saveUsers(users);
//    }
//
//    public void saveCourse(Course course){
//        courseService.saveCourse(course);
//    }
//
//    public void saveSection(Section section){
//        sectionService.registerSection(section);
//    }
//
//    public void saveTutorialGroup(TutorialGroup tutorialGroup){
//        tutorialGroupRepository.save(tutorialGroup);
//    }
//
//    public void saveEnrollment(Enrollment enrollment){
//        enrollmentService.saveEnrollment(enrollment);
//    }
//    public void saveTutorRequest(TutorRequest tutorRequest){
//        tutorRequestService.saveTutorRequest(tutorRequest);
//    }
//
//}
