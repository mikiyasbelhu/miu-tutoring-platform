package edu.miu.cs.cs425.project.miututoring.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.cs.cs425.project.miututoring.api.model.Course;
import edu.miu.cs.cs425.project.miututoring.api.model.Faculty;
import edu.miu.cs.cs425.project.miututoring.api.model.Section;
import edu.miu.cs.cs425.project.miututoring.api.service.SectionService;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

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
class SectionControllerTest {

    private MockMvc mockMvc;
    @Mock
    private SectionService sectionService;
    @InjectMocks
    private SectionController sectionController;

    ObjectMapper om = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(sectionController)
                .build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void listsections() throws Exception{
       mockMvc.perform(get(SectionController.BASE_URL+ "/list").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getSection() throws Exception {

        Faculty faculty1 = new Faculty("faculty@miu.edu","faculty", "Obinna", "A", "Kalu","Software Engineeing CS425");
        Course course = new Course("CS425", "Software Engineering", 4);
        Section section = new Section("CS425-2020-06-01", "Library 109", "2020-06", course, faculty1);

        when(sectionService.getSectionById(1)).thenReturn(section);
        mockMvc.perform(get(SectionController.BASE_URL+"/get/1").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sectionId",is(section.getSectionId())))
                .andExpect(jsonPath("$.sectionName",is("CS425-2020-06-01")))
                .andExpect(jsonPath("$.classRoom", is("Library 109")))
                .andExpect(jsonPath("$.month", is("2020-06")))
                .andExpect(jsonPath("$.course.courseNumber",is(course.getCourseNumber())))
                .andExpect(jsonPath("$.faculty.username",is(section.getFaculty().getUsername())))
                .andReturn();

        verify(sectionService, times(1)).getSectionById(1);
        verifyNoMoreInteractions(sectionService);
    }
}