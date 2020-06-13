package edu.miu.cs.cs425.project.miututoring.api.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class TutorialGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tutorialGroupId;

    @NotBlank
    private String tutorialGroupNumber;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    //to be checked
    @OneToMany(mappedBy = "tutorialGroup")
    private List<Enrollment> enrollments;

    public TutorialGroup() {
    }

    public TutorialGroup(@NotBlank String tutorialGroupNumber, Section section) {
        this.tutorialGroupNumber = tutorialGroupNumber;
        this.section = section;
    }

    public Long getTutorialGroupId() {
        return tutorialGroupId;
    }

    public void setTutorialGroupId(Long tutorialGroupId) {
        this.tutorialGroupId = tutorialGroupId;
    }

    public String getTutorialGroupNumber() {
        return tutorialGroupNumber;
    }

    public void setTutorialGroupNumber(String tutorialGroupNumber) {
        this.tutorialGroupNumber = tutorialGroupNumber;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }
}
