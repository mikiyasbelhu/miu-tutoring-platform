package edu.miu.cs.cs425.project.miututoring.api.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class TutorialGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tutorialGroupId;

    @NotBlank
    private String tutorialGroupNumber;

    //to be checked
    @OneToMany(mappedBy = "tutorialGroup")
    private List<Enrollment> enrollments;

    public TutorialGroup() {
    }

    public TutorialGroup(Long tutorialGroupId, @NotBlank String tutorialGroupNumber) {
        this.tutorialGroupId = tutorialGroupId;
        this.tutorialGroupNumber = tutorialGroupNumber;
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
}
