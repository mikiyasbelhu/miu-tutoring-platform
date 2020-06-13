package edu.miu.cs.cs425.project.miututoring.api.controller;

import edu.miu.cs.cs425.project.miututoring.api.model.Course;
import edu.miu.cs.cs425.project.miututoring.api.model.Report;
import edu.miu.cs.cs425.project.miututoring.api.model.Student;
import edu.miu.cs.cs425.project.miututoring.api.model.TutorialGroup;
import edu.miu.cs.cs425.project.miututoring.api.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping(value = "/api/v1/course", produces = MediaType.APPLICATION_JSON_VALUE)
public class CourseController {

    @Autowired
    CourseService courseService;


    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(value = "/list")
    public Page<Course> getListOfCourses(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer itemsPerPage,
                                         @RequestParam(defaultValue = "") String sortBy, @RequestParam(defaultValue = "false") Boolean sortDesc){
            return courseService.getAllCoursesPaged(page, itemsPerPage, sortBy, sortDesc);
    }

    @GetMapping(value = "/get/{courseId}")
    public Course getCourseById(@PathVariable Integer courseId){
        return courseService.getCourseById(courseId);
    }

    @PutMapping(value = "/edit/{courseId}")
    public Course updateCourse(@Valid @RequestBody Course updatedCourse, @PathVariable Integer courseId){
        return courseService.updateCourse(updatedCourse,courseId);
    }

    @DeleteMapping(value = "/delete/{courseId}")
    public void deleteCourse(@PathVariable Integer courseId){
        courseService.deleteCourseById(courseId);
    }

    @PostMapping(value = "/save")
    public Course saveCourse(@Valid @RequestBody Course course){
        return courseService.saveCourse(course);
    }

    @GetMapping(value = {"/search"})
    public Page<Course> searchCourse(@RequestParam String courseName, @RequestParam String courseNumber, @RequestParam Integer courseCredit,@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer itemsPerPage,
                                     @RequestParam(defaultValue = "") String sortBy, @RequestParam(defaultValue = "false") Boolean sortDesc) {
        return courseService.searchCourses(courseName,courseNumber,courseCredit, page, itemsPerPage, sortBy, sortDesc);
    }


}
