package edu.miu.cs.cs425.project.miututoring.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;

    @NotNull(message = "*Course Number is required")
    private String courseNumber;

    @NotNull(message = "*Course Name is required")
    private String courseName;

    @NotNull(message = "*Course Credit is requried")
    private Integer courseCredit;

    public Course(@NotNull(message = "*Course Number is required") String courseNumber, @NotNull(message = "*Course Name is required") String courseName, @NotNull(message = "*Course Credit is requried") Integer courseCredit) {
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.courseCredit = courseCredit;
    }
    public Course() {
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(Integer courseCredit) {
        this.courseCredit = courseCredit;
    }

    @Override
    public String toString() {
        return String.format(
                "Course [courseNumber=%s, courseName=%s, courseCredit=%s]",
                this.courseNumber,
                this.courseName,
                this.courseCredit);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        boolean result;
        if((obj == null) || (getClass() != obj.getClass())){
            result = false;
        } // end if
        else{
            Course other = (Course) obj;
            result = this.getCourseName().equals(other.getCourseName()) &&  this.getCourseNumber().equals(other.getCourseNumber()) && this.getCourseCredit().equals(other.getCourseCredit());
        } // end else

        return result;
    }
}
