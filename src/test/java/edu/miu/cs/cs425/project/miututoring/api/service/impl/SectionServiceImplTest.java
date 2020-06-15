package edu.miu.cs.cs425.project.miututoring.api.service.impl;

import edu.miu.cs.cs425.project.miututoring.api.AbstractMiuTutoringComponentTest;
import edu.miu.cs.cs425.project.miututoring.api.model.Course;
import edu.miu.cs.cs425.project.miututoring.api.model.Faculty;
import edu.miu.cs.cs425.project.miututoring.api.model.Section;
import edu.miu.cs.cs425.project.miututoring.api.service.CourseService;
import edu.miu.cs.cs425.project.miututoring.api.service.FacultyService;
import edu.miu.cs.cs425.project.miututoring.api.service.SectionService;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@Transactional
class SectionServiceImplTest extends AbstractMiuTutoringComponentTest {
    @Autowired
    private SectionService sectionService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private FacultyService facultyService;

    @BeforeEach
    void setUp() {logger.info("SectionServiceImplTest started");
    }

    @AfterEach
    void tearDown() {logger.info("SectionServiceImplTest completed");
    }

    @Test
    void getAllSections() {
        List<Section> section=sectionService.getAllSections();
        Assert.assertNotNull("Failure: expected sections to be not null", section);
        Assert.assertEquals("Failure: expected size",1, section.size());
        logger.info("Sections list data: " + Arrays.toString(section.toArray()));
    }

    @Test
    void saveSection() {
        Course course2 = new Course("CS525", "EA", 6);
        Faculty faculty2 = new Faculty("abcd@miu.edu","faculty", "Obk", "A",
                "Luka","Engineering ");

        Course savedCourse = courseService.saveCourse(course2);
        Faculty savedFaculty = facultyService.registerFaculty(faculty2);
        Section sectionAB = new Section("AB", "Class AB", "2020-07", savedCourse, savedFaculty);

        Section savedSection=sectionService.saveSection(sectionAB);

        Assert.assertNotNull("Failure: expected not null", savedSection);
        Assert.assertNotNull("Failure: expected sectionId to be not null", savedSection.getSectionId());
        Assert.assertEquals("Failure: expected section course number match", sectionAB.getCourse().getCourseNumber(), savedSection.getCourse().getCourseNumber());
        Assert.assertEquals("Failure: expected section classroom match", sectionAB.getClassRoom(), savedSection.getClassRoom());
        Assert.assertEquals("Failure: expected section month match", sectionAB.getMonth(), savedSection.getMonth());
        Assert.assertEquals("Failure: expected section department match", sectionAB.getFaculty().getDepartment(), savedSection.getFaculty().getDepartment());
        List<Section> sections = sectionService.getAllSections();
        Assert.assertEquals("Failure: expected size", 2, sections.size());
        logger.info("Sections list data: " + Arrays.toString(sections.toArray()));
    }

    @Test
    void getSectionById() {
        Integer sectionId = new Integer(1);
        Section section = sectionService.getSectionById(sectionId);
        Assert.assertNotNull("Failure: expected section to be not null", section);
        Assert.assertEquals("Failure: expected sectionId to match", sectionId, section.getSectionId());
        logger.info("Section data: " + section);
    }
    @Test
    public void testGetSectionByIdForInvalidId() {
        Integer sectionId = Integer.MAX_VALUE;
        Section section = sectionService.getSectionById(sectionId);
        Assert.assertNull("Failure: expected null", section);
        logger.info("Section data: " + section);
    }

    @Test
    void updateSection() {
        Course course2 = new Course("CS525", "EA", 6);
        Faculty faculty2 = new Faculty("abcd@miu.edu","faculty", "Obk", "A",
                "Luka","Engineering ");

        Course savedCourse = courseService.saveCourse(course2);
        Faculty savedFaculty = facultyService.registerFaculty(faculty2);
        Section sectionAB = new Section("AB", "Class AB", "2020-07", savedCourse, savedFaculty);

        Section updated=sectionService.updateSection(sectionAB,1);

        Assert.assertNotNull("Failure: expected not null", updated);
        Assert.assertNotNull("Failure:expected not null",updated.getSectionId());
        Assert.assertEquals("Failure:expected sectionName to match",sectionAB.getSectionName(),updated.getSectionName());
        Assert.assertEquals("Failure:expected classroom to match",sectionAB.getClassRoom(),updated.getClassRoom());
        Assert.assertEquals("Failure:expected courseNumber to match",sectionAB.getCourse().getCourseNumber(),updated.getCourse().getCourseNumber());
        Assert.assertEquals("Failure:expected faculty userName to match",sectionAB.getFaculty().getUsername(),updated.getFaculty().getUsername());
        logger.info("Updated data" + updated);
    }

    @Test
    void deleteSectionById() {

       Integer sectionId = new Integer(1);
       Section section = sectionService.getSectionById(sectionId);
        Assert.assertNotNull("Failure: expected section to be not null", section);
        sectionService.deleteSectionById(sectionId);
        List<Section> sections = sectionService.getAllSections();
        Assert.assertEquals("Failure: expected size", 1, sections.size());
        Section deletedSection = sectionService.getSectionById(sectionId);
        Assert.assertNotNull("Failure: expected deleted Section to be null since is supposed to have been deleted", deletedSection);
        logger.info("Deleted section data"+deletedSection);
    }



}