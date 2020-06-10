package edu.miu.cs.cs425.project.miututoring.api.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Enrollment {
    public enum RoleType{
        TUTEE,
        TUTOR;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer enrollmentId;


    @NotNull
    private RoleType role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "section_id")
    private Section section;

    @ManyToOne
    @JoinColumn(name="tutorial_Group")
    public TutorialGroup tutorialGroup;

    public Enrollment() {
    }

    public Enrollment(  RoleType role, Section section,TutorialGroup tutorialGroup) {
        this.role = role;
        this.section = section;
        this.tutorialGroup = tutorialGroup;
    }


    public Enrollment (RoleType role){
        this.role = role;
    }

    public Integer getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(Integer enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public TutorialGroup getTutorialGroup() {
        return tutorialGroup;
    }

    public void setTutorialGroup(TutorialGroup tutorialGroup) {
        this.tutorialGroup = tutorialGroup;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "role=" + role +
                ", section=" + section +
                ", tutorialGroup=" + tutorialGroup +
                '}';
    }
}
