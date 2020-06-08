package edu.miu.cs.cs425.project.miututoring.api.model;

import com.sun.javafx.beans.IDProperty;
import edu.miu.cs.cs425.project.miututoring.api.model.Section;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="enrollments")
public class Enrollment {
    public enum RoleType{
        TUTEE,TUTOR;
    }
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer enrollmentId;
    @NotBlank(message ="Role Type is required")
    private RoleType role;
    @NotBlank(message ="Section is required")
    @OneToOne(cascade = CascadeType.MERGE)//to b seen!
    @JoinColumn(name = "enrollmentId")
    private Section section;
    @NotBlank(message ="Tutorial Group is required")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "tutorialGroupId")
    private TutorialGroup tutorialGroup;

    public Enrollment() {
    }

    public Enrollment(Integer enrollmentId, @NotBlank RoleType role, Section section, @NotBlank TutorialGroup tutorialGroup) {
        this.enrollmentId = enrollmentId;
        this.role = role;
        this.section = section;
        this.tutorialGroup = tutorialGroup;
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
