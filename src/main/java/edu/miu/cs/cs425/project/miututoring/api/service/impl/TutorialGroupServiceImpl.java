package edu.miu.cs.cs425.project.miututoring.api.service.impl;

import edu.miu.cs.cs425.project.miututoring.api.model.Student;
import edu.miu.cs.cs425.project.miututoring.api.model.TutorialGroup;
import edu.miu.cs.cs425.project.miututoring.api.repository.TutorialGroupRepository;
import edu.miu.cs.cs425.project.miututoring.api.service.TutorialGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorialGroupServiceImpl implements TutorialGroupService {

    TutorialGroupRepository tutorialGroupRepository;

    @Autowired
    public TutorialGroupServiceImpl(TutorialGroupRepository tutorialGroupRepository){
        this.tutorialGroupRepository = tutorialGroupRepository;
    }

    @Override
    public List<TutorialGroup> getAllTutorialGroups() {
        return (List<TutorialGroup>)tutorialGroupRepository.findAll();
    }

    @Override
    public Page<TutorialGroup> getAllTutorialGroupsPaged(int pageNo, Integer pageSize, String sortBy, Boolean sortDesc) {
        return tutorialGroupRepository.findAll(PageRequest.of(pageNo, pageSize == -1 ? Integer.MAX_VALUE :pageSize, sortBy.equals("") ? Sort.unsorted() : Sort.by(sortDesc ? Sort.Direction.DESC :Sort.Direction.ASC ,sortBy)));
    }

    @Override
    public TutorialGroup getTutorialGroupById(Long id) {
        return tutorialGroupRepository.findById(id).orElse(null);
    }

    @Override
    public TutorialGroup registerTutorialGroup(TutorialGroup tutorialGroup) {
        return tutorialGroupRepository.save(tutorialGroup);
    }

    @Override
    public TutorialGroup updateTutorialGroup(TutorialGroup updatedTutorialGroup, Long tutorialGroupId) {
        return tutorialGroupRepository.findById(tutorialGroupId)
                .map(tutorialGroup -> {
                    tutorialGroup.setTutorialGroupNumber(updatedTutorialGroup.getTutorialGroupNumber());
                    return tutorialGroupRepository.save(tutorialGroup);
                })
                .orElseGet(() -> tutorialGroupRepository.save(updatedTutorialGroup));
    }

    @Override
    public void deleteTutorialGroupById(Long tutorialGroupId) {
        tutorialGroupRepository.deleteById(tutorialGroupId);
    }

    @Override
    public List<TutorialGroup> getTutorialGroupBySection(Integer sectionId) {
        return tutorialGroupRepository.findAllBySection_SectionIdEquals(sectionId);
    }

}
