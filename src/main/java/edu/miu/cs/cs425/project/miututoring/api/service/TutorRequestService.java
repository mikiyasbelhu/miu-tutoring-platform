package edu.miu.cs.cs425.project.miututoring.api.service;

import edu.miu.cs.cs425.project.miututoring.api.model.TutorRequest;

import java.util.List;

public interface TutorRequestService {
    List<TutorRequest> listTutorRequests();
    TutorRequest getTutorRequestById(Integer tutorRequestId);
    void deleteTutorRequestById(Integer tutorRequestId);
    TutorRequest saveTutorRequest(TutorRequest tutorRequest);
    TutorRequest updateTutorRequest(TutorRequest tutorRequest, Integer tutorRequestId);
}
