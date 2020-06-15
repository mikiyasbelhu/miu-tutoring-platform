package edu.miu.cs.cs425.project.miututoring.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.cs.cs425.project.miututoring.api.AbstractMiuTutoringComponentTest;
import edu.miu.cs.cs425.project.miututoring.api.model.Course;
import edu.miu.cs.cs425.project.miututoring.api.service.CourseService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import javax.print.attribute.standard.Media;

import static org.hamcrest.CoreMatchers.is;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class CourseControllerTest extends AbstractMiuTutoringComponentTest {

    private MockMvc mockMvc;


    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseController courseController;

    ObjectMapper om = new ObjectMapper();


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(courseController)
                .build();
    }

    @Test
    public void testListCourse() throws Exception {
        mockMvc.perform(get(CourseController.BASE_URL + "/list").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testGetCourse() throws Exception {
        Course course = new Course("CS425", "Software Engineering", 4);
        when(courseService.getCourseById(1)).thenReturn(course);
        mockMvc.perform(get(CourseController.BASE_URL + "/get/1").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.courseId", is(course.getCourseId())))
                .andExpect(jsonPath("$.courseName", is(course.getCourseName())))
                .andExpect(jsonPath("$.courseNumber", is(course.getCourseNumber())))
                .andExpect(jsonPath("$.courseCredit", is(course.getCourseCredit())))
                .andReturn();
        verify(courseService, times(1)).getCourseById(1);
        verifyNoMoreInteractions(courseService);
    }

    @Test
    public void testSaveCourse() throws Exception {
        Course course = new Course("CS420", "Advanced Software Development", 4);
        String jsonRequest = om.writeValueAsString(course);
        when(courseService.saveCourse(course)).thenReturn(course);
        MvcResult result = mockMvc.perform(post(CourseController.BASE_URL+ "/save").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE).characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.courseNumber", is("CS420")))
                .andExpect(jsonPath("$.courseName", is("Advanced Software Development")))
                .andExpect(jsonPath("$.courseCredit", is(4)))
                .andReturn();
        String content = result.getResponse().getContentAsString();
        Assert.assertEquals(content, jsonRequest);
    }
    @Test
    public void testEditCourse() throws Exception{
        Course course = new Course("CS452", "Modern Web Applications", 4);
        String jsonRequest = om.writeValueAsString(course);
        when(courseService.updateCourse(course,1)).thenReturn(course);
        MvcResult result = mockMvc.perform(put(CourseController.BASE_URL+"/edit/1").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE).characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.courseId", is(course.getCourseId())))
                .andExpect(jsonPath("$.courseNumber", is(course.getCourseNumber())))
                .andExpect(jsonPath("$.courseName", is(course.getCourseName())))
                .andExpect(jsonPath("$.courseCredit", is(course.getCourseCredit())))
                .andReturn();
        String content = result.getResponse().getContentAsString();
        Assert.assertEquals(content,jsonRequest);
    }
    @Test
    public void testDeleteCourse() throws Exception{
        mockMvc.perform(delete(CourseController.BASE_URL+"/delete/1"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testSearchCourse() throws  Exception{
        mockMvc.perform(get(CourseController.BASE_URL+ "/search/?searchQuery=4").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }
}
