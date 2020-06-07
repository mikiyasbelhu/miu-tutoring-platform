package edu.miu.cs.cs425.project.miututoring.api;

import edu.miu.cs.cs425.project.miututoring.api.model.Student;
import edu.miu.cs.cs425.project.miututoring.api.model.TutorialGroup;
import edu.miu.cs.cs425.project.miututoring.api.repository.StudentRepository;
import edu.miu.cs.cs425.project.miututoring.api.repository.TutorialGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class MiuTutoring implements CommandLineRunner {

    @Autowired
    StudentRepository studentRepository;

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
    }

}
