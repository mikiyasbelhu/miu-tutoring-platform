package edu.miu.cs.cs425.project.miututoring.api.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@requestId")
public class TutorRequest {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer requestId;

    @ManyToOne
    @JoinColumn(name = "section")
    private Section section;

    @OneToOne
    @JoinColumn(name = "enrollment")
    private Enrollment enrollment;

    private Status status;
    private String experience;

    public enum Status {
        PENDING, ACCEPTED, REJECTED;
    }

    public TutorRequest() {
    }

    public TutorRequest(Section section, Enrollment enrollment, String experience) {
        this.section = section;
        this.enrollment = enrollment;
        this.status = Status.PENDING;
        this.experience = experience;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Enrollment getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return String.format(
                "TutorRequest [section=%s, userId=%s, status=%s, experience=%s]",
                this.section,
                this.enrollment,
                this.status,
                this.experience);
    }

    @Override
    public boolean equals(Object obj) {
        boolean result;
        if((obj == null) || (getClass() != obj.getClass())){
            result = false;
        } // end if
        else{
            TutorRequest other = (TutorRequest) obj;
            result = this.getStatus().equals(other.getStatus()) &&  this.getExperience().equals(other.getExperience()) && this.getSection().getSectionName().equals(other.getSection().getSectionName());
        } // end else
        return result;
    }
}
