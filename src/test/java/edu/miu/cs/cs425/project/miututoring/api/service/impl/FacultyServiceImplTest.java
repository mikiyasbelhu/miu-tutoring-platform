package edu.miu.cs.cs425.project.miututoring.api.service.impl;

import edu.miu.cs.cs425.project.miututoring.api.AbstractMiuTutoringComponentTest;
import edu.miu.cs.cs425.project.miututoring.api.model.Faculty;
import edu.miu.cs.cs425.project.miututoring.api.service.FacultyService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Transactional
public class FacultyServiceImplTest extends  AbstractMiuTutoringComponentTest{

    @Autowired
    private FacultyService facultyService;

    @Before
    public void setUp(){
        logger.info("FacultyServiceImplTest started");
    }

    @After
    public void tearDown(){logger.info("FacultyServiceImplTest completed");}

    @Test
    public void testGetFacultyById() {
        Long facultyId = new Long (5L);
        Faculty faculty = facultyService.getFacultyById(facultyId);
        Assert.assertNotNull("Failure: expected faculty to be not null", faculty);
        Assert.assertEquals("Failure: expected facultyId to match", facultyId, faculty.getId());
        logger.info("Faculty data: " + faculty);
    }

    @Test
    public void testRegisterFaculty(){
        Faculty faculty = new Faculty("faculty4@miu.edu","facu","Trevor","","Noah","Machine Learning CS-588");
        facultyService.registerFaculty(faculty);
        Assert.assertNotNull("Failure: expected not null", faculty);
        Assert.assertNotNull("Failure: expected faculty userName to be not null",faculty.getUsername());
        Assert.assertEquals("Failure: expected factulty department to match", "Machine Learning CS-588", faculty.getDepartment());
        List<Faculty> faculties = facultyService.getAllFaculty();
        Assert.assertEquals("Failure: expected size",2,faculties.size());
        logger.info("Faculities list data: "+ Arrays.toString(faculties.toArray()));
    }

    @Test
    public void testUpdateFaculty(){
        Faculty faculty = new Faculty("faculty5@miu.edu","facu1","John","","Oliver","Advanced System Programming CS-590");
        facultyService.registerFaculty(faculty);
        Faculty updatedFaculty = new Faculty("faculty5@miu.edu","facu1","John","","Oliver","Database Systems CS-329");
        facultyService.updateFaculty(updatedFaculty,faculty.getId());
        Assert.assertNotNull("Failure: expected updated faculty not to be null", updatedFaculty);
        Assert.assertNotNull("Failure: expected updated facultyUserName not to be null", updatedFaculty.getUsername());
        Assert.assertEquals("Failure: excpected Department to be the changed", "Database Systems CS-329",faculty.getDepartment());
        logger.info("Report Data: " + updatedFaculty);
    }

    @Test
    public void testDeleteFaculty(){
        Faculty faculty = new Faculty("faculty5@miu.edu","facu1","John","","Oliver","Advanced System Programming CS-590");
        facultyService.registerFaculty(faculty);
        Assert.assertNotNull("Failure: expected faculty to be not null", faculty);
        List<Faculty> faculties = facultyService.getAllFaculty();
        Assert.assertEquals("Failure: expected size", 2,faculties.size());
        facultyService.deleteFacultyById(faculty.getId());
        Faculty deletedFaculty = facultyService.getFacultyById(faculty.getId());
        Assert.assertNull("Failure: expected deletedFaculty to be null since is supposed to have been deleted", deletedFaculty);
    }









}
