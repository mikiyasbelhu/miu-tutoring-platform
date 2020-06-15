package edu.miu.cs.cs425.project.miututoring.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.miu.cs.cs425.project.miututoring.api.model.*;
import edu.miu.cs.cs425.project.miututoring.api.service.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import javax.transaction.Transactional;
import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class TutorRequestControllerTest {

    private MockMvc mockMvc;

    @Autowired
    StudentService studentService;
    @Autowired
    FacultyService facultyService;
    @Autowired
    CourseService courseService;
    @Autowired
    SectionService sectionService;
    @Autowired
    TutorialGroupService tutorialGroupService;
    @Autowired
    EnrollmentService enrollmentService;


    @Mock
    private TutorRequestService tutorRequestService;

    @InjectMocks
    private TutorRequestController tutorRequestController;

    private ObjectMapper om = new ObjectMapper();


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(tutorRequestController)
                .build();
    }

    @Test
    public void testListTutorRequest() throws Exception {
        mockMvc.perform(get(TutorRequestController.BASE_URL + "/list").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testGetTutorRequest() throws Exception {
        Student student3 = new Student("aaaa@hi2.in", "mine", "000-61-0004", "Tigist", "Gutema", "Kefyalew", 3.84, LocalDate.of(2019, 5, 21));
        studentService.registerStudent(student3);
        Faculty faculty2 = new Faculty("bbbbb@miu.edu", "yours", "Michael", "", "Zijlstra", "Computer Science CS420");
        facultyService.registerFaculty(faculty2);
        Course course1 = new Course("CS420", "MPP", 4);
        courseService.saveCourse(course1);
        Section section2 = new Section("CS420-2020-06-03", "Library 210", "2020-08", course1, faculty2);
        sectionService.saveSection(section2);
        TutorialGroup tutorialGroup1 = new TutorialGroup("CS425", section2);
        tutorialGroupService.registerTutorialGroup(tutorialGroup1);
        Enrollment enrollment1 = new Enrollment(student3, section2);
        enrollmentService.saveEnrollment(enrollment1);
        TutorRequest tutorRequest = new TutorRequest(section2, enrollment1, "I have 10 years of experience");

        when(tutorRequestService.getTutorRequestById(1)).thenReturn(tutorRequest);
        mockMvc.perform(get(TutorRequestController.BASE_URL + "/get/1").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.requestId", is(tutorRequest.getRequestId())))
                .andExpect(jsonPath("$.experience", is("I have 10 years of experience")))
                .andExpect(jsonPath("$.section.sectionId", is(section2.getSectionId())))
                .andExpect(jsonPath("$.enrollment.enrollmentId", is(enrollment1.getEnrollmentId())))
                .andReturn();

        verify(tutorRequestService, times(1)).getTutorRequestById(1);
        verifyNoMoreInteractions(tutorRequestService);
    }

    @Test
    public void testDeleteTutorialRequest() throws Exception {
        mockMvc.perform(delete(TutorRequestController.BASE_URL + "/delete/1"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testEditTutorialRequest() throws Exception {
        Student student3 = new Student("aaaa@hi2.in", "mine", "000-61-0004", "Tigist", "Gutema", "Kefyalew", 3.84, LocalDate.of(2019, 5, 21));
        studentService.registerStudent(student3);
        Faculty faculty2 = new Faculty("bbbbb@miu.edu", "yours", "Michael", "", "Zijlstra", "Computer Science CS420");
        facultyService.registerFaculty(faculty2);
        Course course1 = new Course("CS525", "ASD", 4);
        courseService.saveCourse(course1);
        Section section2 = new Section("CS420-2020-06-03", "Library 210", "2020-08", course1, faculty2);
        sectionService.saveSection(section2);
        TutorialGroup tutorialGroup1 = new TutorialGroup("CS425", section2);
        tutorialGroupService.registerTutorialGroup(tutorialGroup1);
        Enrollment enrollment1 = new Enrollment(student3, section2);
        enrollmentService.saveEnrollment(enrollment1);
        TutorRequest tutorRequest = new TutorRequest(section2, enrollment1, "I have 10 years of experience");
        om.registerModule(new JavaTimeModule());
        om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String jsonRequest = om.writeValueAsString(tutorRequest);
        when(tutorRequestService.updateTutorRequest(tutorRequest, 1)).thenReturn(tutorRequest);
        MvcResult result = mockMvc.perform(put(TutorRequestController.BASE_URL + "/edit/1").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE).characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.requestId", is(tutorRequest.getRequestId())))
                .andExpect(jsonPath("$.experience", is("I have 10 years of experience")))
                .andExpect(jsonPath("$.section.sectionId", is(section2.getSectionId())))
                .andExpect(jsonPath("$.enrollment.enrollmentId", is(enrollment1.getEnrollmentId())))
                .andReturn();
        String content = result.getResponse().getContentAsString();
        Assert.assertNotNull("Failure: expected JSON content to be not null",content);
        verify(tutorRequestService, times(1)).updateTutorRequest(tutorRequest,1);
        verifyNoMoreInteractions(tutorRequestService);
    }
    @Test
    public void testSaveTutorialRequest() throws Exception {
        Student student3 = new Student("aaaa@hi2.in", "mine", "000-61-0004", "Tigist", "Gutema", "Kefyalew", 3.84, LocalDate.of(2019, 5, 21));
        studentService.registerStudent(student3);
        Faculty faculty2 = new Faculty("bbbbb@miu.edu", "yours", "Michael", "", "Zijlstra", "Computer Science CS420");
        facultyService.registerFaculty(faculty2);
        Course course1 = new Course("CS525", "ASD", 4);
        courseService.saveCourse(course1);
        Section section2 = new Section("CS420-2020-06-03", "Library 210", "2020-08", course1, faculty2);
        sectionService.saveSection(section2);
        TutorialGroup tutorialGroup1 = new TutorialGroup("CS425", section2);
        tutorialGroupService.registerTutorialGroup(tutorialGroup1);
        Enrollment enrollment1 = new Enrollment(student3, section2);
        enrollmentService.saveEnrollment(enrollment1);
        TutorRequest tutorRequest = new TutorRequest(section2, enrollment1, "I have 10 years of experience");
        om.registerModule(new JavaTimeModule());
        om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String jsonRequest = om.writeValueAsString(tutorRequest);
        when(tutorRequestService.saveTutorRequest(tutorRequest)).thenReturn(tutorRequest);
        MvcResult result = mockMvc.perform(post(TutorRequestController.BASE_URL + "/save").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE).characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.requestId", is(tutorRequest.getRequestId())))
                .andExpect(jsonPath("$.experience", is("I have 10 years of experience")))
                .andExpect(jsonPath("$.section.sectionId", is(section2.getSectionId())))
                .andExpect(jsonPath("$.enrollment.enrollmentId", is(enrollment1.getEnrollmentId())))
                .andReturn();
        String content = result.getResponse().getContentAsString();
        Assert.assertNotNull("Failure: expected JSON content to be not null",content);
        verify(tutorRequestService, times(1)).saveTutorRequest(tutorRequest);
        verifyNoMoreInteractions(tutorRequestService);
    }
}
