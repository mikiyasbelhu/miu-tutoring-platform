package edu.miu.cs.cs425.project.miututoring.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sectionId;

    private String sectionName;

    @OneToMany(mappedBy = "section")
    @JsonIgnore
    private List<TutorRequest> tutorRequests;

    @NotBlank(message = "Class room is required")
    private String classRoom;

    @NotNull
    private String month;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "courseId")
    private Course course;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @OneToMany
    private List<TutorialGroup> tutorialGroup;

    public Section() {
    }

    public Section(String sectionName, String classRoom, String month, Course course, Faculty faculty) {
        this.sectionName = sectionName;
        this.classRoom = classRoom;
        this.month = month;
        this.course = course;
        this.faculty = faculty;
    }

    public Section(String sectionName, String classRoom, String month, Course course) {
        this.sectionName = sectionName;
        this.classRoom = classRoom;
        this.month = month;
        this.course = course;
    }

    public Section(String sectionName, String classRoom, String month){
        this.sectionName = sectionName;
        this.classRoom = classRoom;
        this.month = month;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public List<TutorialGroup> getTutorialGroup() {
        return tutorialGroup;
    }

    public void setTutorialGroup(List<TutorialGroup> tutorialGroup) {
        this.tutorialGroup = tutorialGroup;
    }

    @Override
    public String toString() {
        return "Section{" +
                "sectionName='" + sectionName + '\'' +
                ", classRoom='" + classRoom + '\'' +
                ", month=" + month +
                ", course=" + course +
                ", faculty=" + faculty +
                ", tutorialGroup=" + tutorialGroup +
                '}';
    }

}
