package edu.miu.cs.cs425.project.miututoring.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.cs.cs425.project.miututoring.api.AbstractMiuTutoringComponentTest;
import edu.miu.cs.cs425.project.miututoring.api.model.Faculty;
import edu.miu.cs.cs425.project.miututoring.api.service.FacultyService;
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
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringRunner.class)
@SpringBootTest
public class FacultyControllerTest  extends AbstractMiuTutoringComponentTest {

    private MockMvc mockMvc;


    @Mock
    private FacultyService facultyService;

    @InjectMocks
    private FacultyController facultyController;

    ObjectMapper om = new ObjectMapper();

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(facultyController)
                .build();
    }

    @Test
    public void testListFaculty() throws Exception{
            mockMvc.perform(get(FacultyController.BASE_URL+ "/list").contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isOk())
                    .andReturn();
    }

    @Test
    public void testGetFaculty() throws Exception{

        Faculty faculty = new Faculty("faculty@miu.edu","kalu","Obinna","A","Kalu","Software Engineeing CS425");
        when(facultyService.getFacultyById(5L)).thenReturn(faculty);
        mockMvc.perform(get(FacultyController.BASE_URL+"/get/5").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.id",is(faculty.getId())))
                .andExpect(jsonPath("$.username",is("faculty@miu.edu")))
                .andExpect(jsonPath("$.firstName", is("Obinna")))
                .andExpect(jsonPath("$.middleName", is("A")))
                .andExpect(jsonPath("$.lastName",is(faculty.getLastName())))
                .andExpect(jsonPath("$.roles",is(faculty.getRoles())))
                .andExpect(jsonPath("$.department", is(faculty.getDepartment())))
                .andReturn();

        verify(facultyService, times(1)).getFacultyById(5L);
        verifyNoMoreInteractions(facultyService);
    }
    @Test
    public void testRegisterFaculty() throws Exception{
        Faculty faculty = new Faculty("mikeFaculty@miu.edu","kalu","Tseganesh","M","Hailemariam","Software Engineeing CS425");
        String jsonRequest = om.writeValueAsString(faculty);
        when(facultyService.registerFaculty(faculty)).thenReturn(faculty);

        MvcResult result = mockMvc.perform(post(FacultyController.BASE_URL+"/register").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.username", is("mikeFaculty@miu.edu")))
                .andExpect(jsonPath("$.firstName", is("Tseganesh")))
                .andExpect(jsonPath("$.middleName", is("M")))
                .andExpect(jsonPath("$.lastName", is("Hailemariam")))
                .andExpect(jsonPath("$.roles", is(faculty.getRoles())))
                .andExpect(jsonPath("$.department", is("Software Engineeing CS425")))
                .andReturn();

        String content = result.getResponse().getContentAsString();
       assertEquals(content, jsonRequest);


    }

    @Test
    public void testUpdateFaculty() throws Exception{
        Faculty faculty = new Faculty("mikeFaculty@miu.edu","kalu","Tseganesh","M","Hailemariam","Software Engineeing CS425");
        Faculty updatedFaculty = new Faculty("mikeFaculty@miu.edu","kalu","Tseganesh","M","Hailemariam","Software Engineeing CS425");

        String jsonRequest = om.writeValueAsString(updatedFaculty);
        when(facultyService.getFacultyById(faculty.getId())).thenReturn(faculty);
        when(facultyService.registerFaculty(updatedFaculty)).thenReturn(updatedFaculty);

        mockMvc.perform(put(FacultyController.BASE_URL+"/edit/1").content(jsonRequest).contentType((MediaType.APPLICATION_JSON_VALUE)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testDeleteFaculty() throws Exception{
        mockMvc.perform(delete(FacultyController.BASE_URL+"/delete/5"))
                .andExpect(status().isOk())
                .andReturn();
    }



}
