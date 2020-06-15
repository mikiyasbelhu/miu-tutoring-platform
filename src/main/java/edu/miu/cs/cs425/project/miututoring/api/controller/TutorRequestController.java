package edu.miu.cs.cs425.project.miututoring.api.controller;

import edu.miu.cs.cs425.project.miututoring.api.model.Enrollment;
import edu.miu.cs.cs425.project.miututoring.api.model.TutorRequest;
import edu.miu.cs.cs425.project.miututoring.api.model.TutorialGroup;
import edu.miu.cs.cs425.project.miututoring.api.service.TutorRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.invocation.ReactiveReturnValueHandler;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
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
    public Page<TutorRequest> getListOfTutorRequests(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer itemsPerPage,
                                                     @RequestParam(defaultValue = "") String sortBy, @RequestParam(defaultValue = "false") Boolean sortDesc){
        return tutorRequestService.listTutorRequestsPaged(page,itemsPerPage,sortBy,sortDesc);
    }

    @GetMapping(value = "/get/{tutorRequestId}")
    public TutorRequest getTutorRequestById(@PathVariable Integer tutorRequestId){
        return tutorRequestService.getTutorRequestById(tutorRequestId);
    }

    @DeleteMapping(value = "/delete/{tutorRequestId}")
    public void deleteTutorRequestById(@PathVariable Integer tutorRequestId){
        tutorRequestService.deleteTutorRequestById(tutorRequestId);
    }

    @PostMapping(value = "/save")
    public TutorRequest saveTutorRequest(@Valid @RequestBody TutorRequest tutorRequest){
        return tutorRequestService.saveTutorRequest(tutorRequest);
    }
    @PutMapping(value = "/edit/{tutorRequestId}")
    public TutorRequest editTutorRequest(@Valid @RequestBody TutorRequest tutorRequest, @PathVariable Integer tutorRequestId){
        return tutorRequestService.updateTutorRequest(tutorRequest,tutorRequestId);
    }
    @PostMapping(value = "/accept/{tutorRequestId}")
    public TutorRequest acceptTutorRequest(@Valid @RequestBody TutorialGroup tutorialGroup, @PathVariable Integer tutorRequestId){
        return tutorRequestService.acceptTutorRequest(tutorRequestId, tutorialGroup);
    }

    @GetMapping(value = "/deny/{tutorRequestId}")
    public TutorRequest denyTutorRequest(@PathVariable Integer tutorRequestId){
        return tutorRequestService.denyTutorRequest(tutorRequestId);
    }

    @GetMapping(value="/getbystudent/{studentId}")
    public Page<TutorRequest> getTutorRequestByStudent(@PathVariable Long studentId, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer itemsPerPage,
                                                       @RequestParam(defaultValue = "") String sortBy, @RequestParam(defaultValue = "false") Boolean sortDesc) {
        return tutorRequestService.getTutorRequestByStudent(studentId,page,itemsPerPage,sortBy,sortDesc);
    }

}
