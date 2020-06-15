package edu.miu.cs.cs425.project.miututoring.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.cs.cs425.project.miututoring.api.AbstractMiuTutoringComponentTest;
import edu.miu.cs.cs425.project.miututoring.api.model.Student;
import edu.miu.cs.cs425.project.miututoring.api.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentControllerTest extends AbstractMiuTutoringComponentTest {

    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    ObjectMapper om = new ObjectMapper();

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(studentController)
                .build();
    }

    @Test
    public void testListStudent() throws Exception{
            mockMvc.perform(get(StudentController.BASE_URL+ "/list").contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isOk())
                    .andReturn();
    }

    @Test
    public void testGetStudent() throws Exception{

        Student student = new Student("student7@miu.edu", "student", "000-61-0007", "John", "Kaleb", "Peters", 3.54, LocalDate.of(2019, 5, 21));
        when(studentService.getStudentById(5L)).thenReturn(student);
        mockMvc.perform(get(StudentController.BASE_URL+"/get/5").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.id",is(student.getId())))
                .andExpect(jsonPath("$.username",is("student7@miu.edu")))
                .andExpect(jsonPath("$.firstName", is("John")))
                .andExpect(jsonPath("$.middleName", is("Kaleb")))
                .andExpect(jsonPath("$.lastName",is(student.getLastName())))
                .andExpect(jsonPath("$.roles",is(student.getRoles())))
                .andExpect(jsonPath("$.cgpa",is(student.getCgpa())))
                .andReturn();

        verify(studentService, times(1)).getStudentById(5L);
        verifyNoMoreInteractions(studentService);
    }
    @Test
    public void testRegisterStudent() throws Exception{
        Student student = new Student("student8@miu.edu", "student", "000-61-0007", "John", "Kaleb", "Peters", 3.54);
        String jsonRequest = om.writeValueAsString(student);
        when(studentService.registerStudent(student)).thenReturn(student);

        MvcResult result = mockMvc.perform(post(StudentController.BASE_URL+"/register").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.username", is("student8@miu.edu")))
                .andExpect(jsonPath("$.firstName", is("John")))
                .andExpect(jsonPath("$.middleName", is("Kaleb")))
                .andExpect(jsonPath("$.lastName", is("Peters")))
                .andExpect(jsonPath("$.roles", is(student.getRoles())))
                .andExpect(jsonPath("$.cgpa",is(student.getCgpa())))
                .andReturn();

        String content = result.getResponse().getContentAsString();
       assertEquals(content, jsonRequest);
    }

    @Test
    public void testUpdateStudent() throws Exception{
        Student student = new Student("student9@miu.edu", "student", "000-61-0007", "John", "Kaleb", "Peters", 3.54);
        Student updatedStudent = new Student("student9@miu.edu", "student", "000-61-0007", "John", "Kaleb", "Peters", 3.54);

        String jsonRequest = om.writeValueAsString(updatedStudent);
        when(studentService.getStudentById(student.getId())).thenReturn(student);
        when(studentService.registerStudent(updatedStudent)).thenReturn(updatedStudent);

        mockMvc.perform(put(StudentController.BASE_URL+"/edit/1").content(jsonRequest).contentType((MediaType.APPLICATION_JSON_VALUE)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testDeleteStudent() throws Exception{
        mockMvc.perform(delete(StudentController.BASE_URL+"/delete/5"))
                .andExpect(status().isOk())
                .andReturn();
    }



}
