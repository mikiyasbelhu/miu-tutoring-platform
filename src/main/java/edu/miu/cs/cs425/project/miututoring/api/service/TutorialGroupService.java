package edu.miu.cs.cs425.project.miututoring.api.service;

import edu.miu.cs.cs425.project.miututoring.api.model.Student;
import edu.miu.cs.cs425.project.miututoring.api.model.TutorialGroup;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TutorialGroupService {
    List<TutorialGroup> getAllTutorialGroups();
    Page<TutorialGroup> getAllTutorialGroupsPaged(int pageNo, Integer pageSize, String sortBy, Boolean sortDesc);
    TutorialGroup getTutorialGroupById(Long id);
    TutorialGroup registerTutorialGroup(TutorialGroup tutorialGroup);
    TutorialGroup updateTutorialGroup(TutorialGroup updatedTutorialGroup, Long tutorialGroupId);
    void deleteTutorialGroupById(Long tutorialGroupId);
}
