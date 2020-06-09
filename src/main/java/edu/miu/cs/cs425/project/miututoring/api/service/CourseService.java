package edu.miu.cs.cs425.project.miututoring.api.service;

import edu.miu.cs.cs425.project.miututoring.api.model.Course;


import java.util.List;


public interface CourseService {

    List<Course> getAllCourses();
    Course saveCourse(Course course);
    Course updateCourse(Course updatedCourse, Integer courseId);
    void deleteCourseById(Integer courseId);
}
