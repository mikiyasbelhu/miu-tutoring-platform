package edu.miu.cs.cs425.project.miututoring.api.controller;

import edu.miu.cs.cs425.project.miututoring.api.model.TutorRequest;
import edu.miu.cs.cs425.project.miututoring.api.service.TutorRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/tutorialrequest", produces = MediaType.APPLICATION_JSON_VALUE)
public class TutorRequestController {

    @Autowired
    TutorRequestService tutorRequestService;

    public TutorRequestController(TutorRequestService tutorRequestService) {
        this.tutorRequestService = tutorRequestService;
    }

    @GetMapping(value = "/list")
    public List<TutorRequest> getListOfTutorRequests(){
        return tutorRequestService.listTutorRequests();
    }

    @GetMapping(value = "/get/{tutorrequestId}")
    public TutorRequest getTutorRequestById(@PathVariable Integer tutorRequestId){
        return tutorRequestService.getTutorRequestById(tutorRequestId);
    }

    @DeleteMapping(value = "/delete/{tutorrequestId}")
    public void deleteTutorRequestById(@PathVariable Integer tutorRequestId){
        tutorRequestService.deleteTutorRequestById(tutorRequestId);
    }

    @PostMapping(value = "/savetutorrequest")
    public TutorRequest saveTutorRequest(@Valid @RequestBody TutorRequest tutorRequest){
        return tutorRequestService.saveTutorRequest(tutorRequest);
    }
    @PutMapping(value = "/edit/{tutorrequestId}")
    public TutorRequest editTutorRequest(@Valid @RequestBody TutorRequest tutorRequest, @PathVariable Integer tutorRequestId){
        return tutorRequestService.updateTutorRequest(tutorRequest,tutorRequestId);
    }
}
