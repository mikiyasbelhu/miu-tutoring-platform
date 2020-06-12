package edu.miu.cs.cs425.project.miututoring.api.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Student extends User {

    @NotBlank
    private String studentNumber;

    private Double cgpa;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate enrollmentDate;

    @OneToMany(mappedBy = "student", cascade = CascadeType.PERSIST)
    List<Report> reports;

    @OneToOne(cascade = CascadeType.PERSIST)
    TutorRequest tutorRequest;



    public Student() {
    }

    public Student(String username,String password, @NotBlank String studentNumber, @NotBlank String firstName, String middleName, @NotBlank String lastName, Double cgpa, LocalDate enrollmentDate) {
        super(username,password,firstName, middleName, lastName, new ArrayList<>(Arrays.asList("ROLE_STUDENT")));
        this.studentNumber = studentNumber;
        this.cgpa = cgpa;
        this.enrollmentDate = enrollmentDate;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public Double getCgpa() {
        return cgpa;
    }

    public void setCgpa(Double cgpa) {
        this.cgpa = cgpa;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

}
