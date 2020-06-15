package edu.miu.cs.cs425.project.miututoring.api.controller;

import edu.miu.cs.cs425.project.miututoring.api.model.*;
import edu.miu.cs.cs425.project.miututoring.api.service.EnrollmentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
class EnrollmentControllerTest {
    private MockMvc mockMvc;
    @Mock
    private EnrollmentService enrollmentService;
    @InjectMocks
    private EnrollmentController enrollmentController;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(enrollmentController)
                .build();

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void listOfEnrollment() throws Exception{
        mockMvc.perform(get(EnrollmentController.BASE_URL+ "/list").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getEnrollment() throws Exception{
        Student student1 = new Student("student@miu.edu","student","000-61-0001", "Tesfaye", "Lemma", "Girum", 3.45, LocalDate.of(2019,5,24));
        Faculty faculty1 = new Faculty("faculty@miu.edu","faculty", "Obinna", "A", "Kalu","Software Engineeing CS425");
        Course course = new Course("CS425", "Software Engineering", 4);
        Section section = new Section("CS425-2020-06-01", "Library 109", "2020-06", course,faculty1);

        Enrollment enrollment=new Enrollment(student1,section);

        when(enrollmentService.getEnrollmentById(1)).thenReturn(enrollment);
        mockMvc.perform(get(EnrollmentController.BASE_URL+"/get/1").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.enrollmentId",is(enrollment.getEnrollmentId())))
                .andExpect(jsonPath("$.section.sectionId", is(enrollment.getSection().getSectionId())))
                .andExpect(jsonPath("$.student.id", is(enrollment.getStudent().getId())))
                .andExpect(jsonPath("$.section.course.courseNumber",is(enrollment.getSection().getCourse().getCourseNumber())))
                .andExpect(jsonPath("$.section.faculty.username",is(enrollment.getSection().getFaculty().getUsername())))
                .andReturn();

        verify(enrollmentService, times(1)).getEnrollmentById(1);
        verifyNoMoreInteractions(enrollmentService);
    }
}