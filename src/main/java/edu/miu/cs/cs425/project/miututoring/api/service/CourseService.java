package edu.miu.cs.cs425.project.miututoring.api.service;

import edu.miu.cs.cs425.project.miututoring.api.model.Course;
import edu.miu.cs.cs425.project.miututoring.api.model.Report;
import edu.miu.cs.cs425.project.miututoring.api.model.Student;
import edu.miu.cs.cs425.project.miututoring.api.model.TutorialGroup;
import org.springframework.data.domain.Page;


import java.util.List;


public interface CourseService {

    List<Course> getAllCourses();
    Course saveCourse(Course course);
    Course updateCourse(Course updatedCourse, Integer courseId);
    void deleteCourseById(Integer courseId);
    Course getCourseById(Integer reportId);
    Page<Course> getAllCoursesPaged(int pageNo, Integer pageSize, String sortBy, Boolean sortDesc);
    Page<Course> searchCourses(String courseName, String courseNumber, Integer courseCredit, int pageNo, Integer pageSize, String sortBy, Boolean sortDesc);
}
