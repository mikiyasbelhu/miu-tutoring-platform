package edu.miu.cs.cs425.project.miututoring.api.service.impl;

import edu.miu.cs.cs425.project.miututoring.api.AbstractMiuTutoringComponentTest;
import edu.miu.cs.cs425.project.miututoring.api.model.Course;
import edu.miu.cs.cs425.project.miututoring.api.model.Section;
import edu.miu.cs.cs425.project.miututoring.api.service.CourseService;
import edu.miu.cs.cs425.project.miututoring.api.service.SectionService;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@Transactional
class CourseServiceImplTest extends AbstractMiuTutoringComponentTest {

    @Autowired
    CourseService courseService;

    @Autowired
    SectionService sectionService;

    @BeforeEach
    void setUp() {
        logger.info("CourseServiceImplTest started");
    }

    @AfterEach
    void tearDown() {
        logger.info("CourseServiceImplTest completed");
    }

    @Test
    void deleteCourseById() {
        Section updater = sectionService.getSectionById(1);
        updater.setCourse(null);
        sectionService.updateSection(updater,1);
        Course toBeDeleted = courseService.getCourseById(1);
        Assert.assertNotNull("Failure: Expected course to be not null", toBeDeleted);
        courseService.deleteCourseById(1);
        Course deletedCourse = courseService.getCourseById(1);
        Assert.assertNull("Expected course to be deleted",deletedCourse);
    }
    @Test
    public void testGetAllCourses(){
        List<Course> course = courseService.getAllCourses();
        Assert.assertNotNull("Failure: expected course to be not null", course);
        Assert.assertEquals("Failure: expected size", 1, course.size());
        logger.info("Course list data: " + Arrays.toString(course.toArray()));
    }
    @Test
    public void testSaveCourse(){
        Course course = new Course("1","SE",5);
        Course savedCourse = courseService.saveCourse(course);
        Assert.assertNotNull("Failure: expected not null", savedCourse);
        Assert.assertNotNull("Failure expected courseId to be not null", savedCourse.getCourseId());
        Assert.assertNotNull("Failure expected courseNumber to be not null", savedCourse.getCourseNumber());
        Assert.assertNotNull("Failure expected courseName to be not null,", savedCourse.getCourseName());
        Assert.assertNotNull("Failure expected courseCredit to be not null", savedCourse.getCourseCredit());
        List<Course> courses = courseService.getAllCourses();
        Assert.assertEquals("Failure: expected size", 2, courses.size());
        logger.info("Courses list data: "+ Arrays.toString((courses.toArray())));
    }
}