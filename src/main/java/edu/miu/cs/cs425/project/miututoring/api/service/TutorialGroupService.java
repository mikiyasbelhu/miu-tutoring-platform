package edu.miu.cs.cs425.project.miututoring.api.service;

import edu.miu.cs.cs425.project.miututoring.api.model.TutorialGroup;

import java.util.List;

public interface TutorialGroupService {
    List<TutorialGroup> getAllTutorialGroups();
    TutorialGroup getTutorialGroupById(Long id);
    TutorialGroup registerTutorialGroup(TutorialGroup tutorialGroup);
    TutorialGroup updateTutorialGroup(TutorialGroup updatedTutorialGroup, Long tutorialGroupId);
    void deleteTutorialGroupById(Long tutorialGroupId);
}
