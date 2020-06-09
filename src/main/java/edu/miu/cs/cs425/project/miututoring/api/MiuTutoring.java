package edu.miu.cs.cs425.project.miututoring.api;

import edu.miu.cs.cs425.project.miututoring.api.model.*;
import edu.miu.cs.cs425.project.miututoring.api.repository.StudentRepository;
import edu.miu.cs.cs425.project.miututoring.api.repository.TutorialGroupRepository;
import edu.miu.cs.cs425.project.miututoring.api.service.CourseService;
import edu.miu.cs.cs425.project.miututoring.api.service.EnrollmentService;
import edu.miu.cs.cs425.project.miututoring.api.service.FacultyService;
import edu.miu.cs.cs425.project.miututoring.api.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MiuTutoring implements CommandLineRunner {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    EnrollmentService enrollmentService;

    @Autowired
    SectionService sectionService;

    @Autowired
    CourseService courseService;

    @Autowired
    FacultyService facultyService;

    @Autowired
    TutorialGroupRepository tutorialGroupRepository;

    public static void main(String[] args) {
        SpringApplication.run(MiuTutoring.class, args);
    }

    public void saveStudent(Student student){
        studentRepository.save(student);
    }

    public void saveTutorialGroup(TutorialGroup tutorialGroup){
        tutorialGroupRepository.save(tutorialGroup);
    }

    @Override
    public void run(String... args) throws Exception {
        Student student1 = new Student(1L,"000-61-0001", "Anna", "Lynn", "Smith", 3.45, LocalDate.of(2019,5,24));
        Student student2 = new Student(2L,"000-61-0002", "John", "Trester", "George", 3.24, LocalDate.of(2019,5,21));
        saveStudent(student1);
        saveStudent(student2);

        TutorialGroup tutorialGroup1 = new TutorialGroup(1L,"CS425");
        TutorialGroup tutorialGroup2 = new TutorialGroup(2L,"CS575");
        saveTutorialGroup(tutorialGroup1);
        saveTutorialGroup(tutorialGroup2);

        List<TutorialGroup> list =  new ArrayList<>();
        list.add(tutorialGroup1);

        Course course1 = new Course("CS425","SE", 4);
        Faculty faculty = new Faculty("ComPro");

        //courseService.saveCourse(course1);

        Section section1 = new Section("A","Class A", Section.BlockMonth.APRIL);
        Section section2 = new Section("B","Class B", Section.BlockMonth.APRIL,course1,faculty);

        sectionService.saveSection(section1);
        sectionService.saveSection(section2);
        System.out.println("Sections saved");

        Enrollment enrollment1 = new Enrollment(Enrollment.RoleType.TUTEE);
        Enrollment enrollment2 = new Enrollment(Enrollment.RoleType.TUTOR, section1, tutorialGroup1);

        enrollmentService.saveEnrollment(enrollment1);
        //enrollmentService.saveEnrollment(enrollment2);
        System.out.println("Enrollment saved");


    }

}