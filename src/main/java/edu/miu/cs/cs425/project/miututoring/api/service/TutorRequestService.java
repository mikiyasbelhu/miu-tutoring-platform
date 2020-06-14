package edu.miu.cs.cs425.project.miututoring.api.service;

import edu.miu.cs.cs425.project.miututoring.api.model.TutorRequest;
import edu.miu.cs.cs425.project.miututoring.api.model.TutorialGroup;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TutorRequestService {
    Page<TutorRequest> listTutorRequests(int pageNo,Integer pageSize, String sortBy, Boolean sortDesc);
    TutorRequest getTutorRequestById(Integer tutorRequestId);
    void deleteTutorRequestById(Integer tutorRequestId);
    TutorRequest saveTutorRequest(TutorRequest tutorRequest);
    TutorRequest updateTutorRequest(TutorRequest tutorRequest, Integer tutorRequestId);
    TutorRequest acceptTutorRequest(Integer tutorRequestId, TutorialGroup tutorialGroup);
    TutorRequest denyTutorRequest(Integer tutorRequestId);
    Page<TutorRequest> getTutorRequestByStudent(Long studentId, Integer page, Integer itemsPerPage, String sortBy, Boolean sortDesc);
}
