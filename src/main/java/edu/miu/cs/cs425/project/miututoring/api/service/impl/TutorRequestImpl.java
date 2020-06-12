package edu.miu.cs.cs425.project.miututoring.api.service.impl;

import edu.miu.cs.cs425.project.miututoring.api.model.TutorRequest;
import edu.miu.cs.cs425.project.miututoring.api.repository.TutorRequestRepository;
import edu.miu.cs.cs425.project.miututoring.api.service.TutorRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorRequestImpl implements TutorRequestService {
    @Autowired
    TutorRequestRepository tutorRequestRepository;

    public TutorRequestImpl(TutorRequestRepository tutorRequestRepository) {
        this.tutorRequestRepository = tutorRequestRepository;
    }

    @Override
    public List<TutorRequest> listTutorRequests() {
        return (List<TutorRequest>) tutorRequestRepository.findAll();
    }

    @Override
    public TutorRequest getTutorRequestById(Integer tutorRequestId) {
        return tutorRequestRepository.findById(tutorRequestId).orElse(null);
    }

    @Override
    public void deleteTutorRequestById(Integer tutorRequestId) {
        tutorRequestRepository.deleteById(tutorRequestId);
    }

    @Override
    public TutorRequest saveTutorRequest(TutorRequest tutorRequest) {
        return tutorRequestRepository.save(tutorRequest);
    }

    @Override
    public TutorRequest updateTutorRequest(TutorRequest updatedTutorRequest, Integer tutorRequestId) {
        return tutorRequestRepository.findById(tutorRequestId).map(tutorRequest -> {
            tutorRequest.setSection(updatedTutorRequest.getSection());
            tutorRequest.setStatus(updatedTutorRequest.getStatus());
            tutorRequest.setUserId(updatedTutorRequest.getUserId());
            return tutorRequestRepository.save(tutorRequest);
        }).orElseGet(()-> tutorRequestRepository.save(updatedTutorRequest));
    }

    @Override
    public TutorRequest acceptTutorRequest(Integer tutorRequestId) {
        return tutorRequestRepository.findById(tutorRequestId).map(tutorRequest -> {
            tutorRequest.setStatus(TutorRequest.Status.ACCEPTED);
            return tutorRequestRepository.save(tutorRequest);
        }).orElse(null);
    }

    @Override
    public TutorRequest denyTutorRequest(Integer tutorRequestId) {
        return tutorRequestRepository.findById(tutorRequestId).map(tutorRequest -> {
            tutorRequest.setStatus(TutorRequest.Status.REJECTED);
            return tutorRequestRepository.save(tutorRequest);
        }).orElse(null);
    }
}
