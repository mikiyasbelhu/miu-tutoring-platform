package edu.miu.cs.cs425.project.miututoring.api.model;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name="sections")
public class Section {
    public enum BlockMonth{
        JANUARY,FEBRUARY,MARCH,APRIL,MAY,JUNE,JULY,AUGUST,SEPTEMBER,OCTOBER,NOVEMBER,DECEMBER
    }
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer sectionId;
    private String sectionName;
    @NotBlank(message ="Class room is required")
    private String classRoom;
    @NotBlank(message ="Block Month is required")
    private BlockMonth month;
     @NotBlank(message ="Course is required")
     @OneToOne(cascade = CascadeType.MERGE)
     @JoinColumn(name = "sectionId")
    private Course course;                                                 //assuming the relation is one directional,
     @NotBlank(message ="Faculty is required")
     @ManyToOne                                                          //(name ="faculty_by",nullable=false)
    private Faculty faculty;
     @NotBlank(message ="Tutorial Group is required")
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "tutorialGroupId")
    private List<TutorialGroup> tutorialGroup;

    public Section() {
    }

    public Section(String sectionName, String classRoom, BlockMonth month, Course course, Faculty faculty, List<TutorialGroup> tutorialGroup) {
        this.sectionName = sectionName;
        this.classRoom = classRoom;
        this.month = month;
        this.course = course;
        this.faculty = faculty;
        this.tutorialGroup = tutorialGroup;
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

    public BlockMonth getMonth() {
        return month;
    }

    public void setMonth(BlockMonth month) {
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
