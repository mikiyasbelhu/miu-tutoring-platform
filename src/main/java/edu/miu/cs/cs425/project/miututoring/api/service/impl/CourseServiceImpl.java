package edu.miu.cs.cs425.project.miututoring.api.service.impl;

import edu.miu.cs.cs425.project.miututoring.api.model.Course;
import edu.miu.cs.cs425.project.miututoring.api.model.Report;
import edu.miu.cs.cs425.project.miututoring.api.repository.CourseRepository;
import edu.miu.cs.cs425.project.miututoring.api.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseRepository courseRepository;


    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Course updatedCourse, Integer courseId) {
        return courseRepository.findById(courseId).map(course -> {
            course.setCourseCredit(updatedCourse.getCourseCredit());
            course.setCourseId(updatedCourse.getCourseId());
            course.setCourseName(updatedCourse.getCourseName());
            course.setCourseNumber(updatedCourse.getCourseNumber());
            return courseRepository.save(course);
        })
                .orElseGet(() -> courseRepository.save(updatedCourse));
    }

    @Override
    public void deleteCourseById(Integer courseId) {
        courseRepository.deleteById(courseId);
    }

    @Override
    public Course getCourseById(Integer reportId) {
        return courseRepository.findById(reportId).orElse(null);
    }

    @Override
    public Page<Course> getAllCoursesPaged(int pageNo, Integer pageSize, String sortBy, Boolean sortDesc) {
        return courseRepository.findAll(PageRequest.of(pageNo, pageSize == -1 ? Integer.MAX_VALUE : pageSize, Sort.by(sortDesc ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy)));
    }

    @Override
    public Page<Course> searchCourses(String courseName, String courseNumber, Integer courseCredit, int pageNo, Integer pageSize, String sortBy, Boolean sortDesc) {
        return  courseRepository.findAllByCourseNameContainingOrCourseNumberContainingOrCourseCreditContaining(courseName,courseNumber,courseCredit,PageRequest.of(pageNo, pageSize, Sort.by(sortDesc ? Sort.Direction.DESC :Sort.Direction.ASC ,sortBy)));
    }

}
