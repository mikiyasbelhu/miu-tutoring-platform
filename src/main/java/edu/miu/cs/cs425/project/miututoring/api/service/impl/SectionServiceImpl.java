package edu.miu.cs.cs425.project.miututoring.api.service.impl;

import edu.miu.cs.cs425.project.miututoring.api.model.Section;
import edu.miu.cs.cs425.project.miututoring.api.repository.SectionRepository;
import edu.miu.cs.cs425.project.miututoring.api.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionServiceImpl implements SectionService {
    private SectionRepository sectionRepository;

    @Autowired
    public SectionServiceImpl(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    @Override
    public List<Section> getAllSections() {
        return (List<Section>) sectionRepository.findAll();
    }

    @Override
    public Section saveSection(Section section){
        return sectionRepository.save(section);
    }

    @Override
    public Section getSectionById(Integer sectionId) {
        return sectionRepository.findById(sectionId).orElse(null);
    }

    @Override
    public Section updateSection(Section updatedSection, Integer enrollmentId) {
        return sectionRepository.findById(enrollmentId).map(section -> {
            section.setClassRoom(updatedSection.getClassRoom());
            section.setCourse(updatedSection.getCourse());
            section.setFaculty(updatedSection.getFaculty());
            section.setMonth(updatedSection.getMonth());
            section.setSectionName(updatedSection.getSectionName());
            section.setTutorialGroup(updatedSection.getTutorialGroup());
            return sectionRepository.save(section);
        }).orElseGet(()->sectionRepository.save(updatedSection));
    }

    @Override
    public void deleteSectionById(Integer sectionId) {
        sectionRepository.findById(sectionId);

    }

    @Override
    public Section registerSection(Section section) {
        return sectionRepository.save(section);
    }

    @Override
    public Page<Section> getAllSectionsPaged(Integer page, Integer itemsPerPage, String sortBy, Boolean sortDesc) {
        return sectionRepository.findAll(PageRequest.of(page, itemsPerPage == -1 ? Integer.MAX_VALUE :itemsPerPage, sortBy.equals("") ? Sort.unsorted() : Sort.by(sortDesc ? Sort.Direction.DESC :Sort.Direction.ASC ,sortBy)));
    }
}
