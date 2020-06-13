package edu.miu.cs.cs425.project.miututoring.api.service;

import edu.miu.cs.cs425.project.miututoring.api.model.Enrollment;
import edu.miu.cs.cs425.project.miututoring.api.model.Section;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SectionService {
    List<Section> getAllSections();
    Section getSectionById(Integer sectionId);
    Section saveSection(Section section);
    Section updateSection(Section updatedSection, Integer enrollmentId);
    void deleteSectionById(Integer sectionId);
    Section registerSection(Section section);

    Page<Section> getAllSectionsPaged(Integer page, Integer itemsPerPage, String sortBy, Boolean sortDesc);
}
