package edu.miu.cs.cs425.project.miututoring.api.repository;

import edu.miu.cs.cs425.project.miututoring.api.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    Page<Course> findAllByCourseNameContainingOrCourseNumberContainingOrCourseCreditContaining(@NotNull(message = "*Course Name is required") String courseName, @NotNull(message = "*Course Number is required") String courseNumber, @NotNull(message = "*Course Credit is requried") Integer courseCredit, Pageable pageable);
}
