package edu.miu.cs.cs425.project.miututoring.api.controller;

import edu.miu.cs.cs425.project.miututoring.api.model.TutorRequest;
import edu.miu.cs.cs425.project.miututoring.api.model.TutorialGroup;
import edu.miu.cs.cs425.project.miututoring.api.service.TutorialGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/tutorialgroup", produces = MediaType.APPLICATION_JSON_VALUE)
public class TutorialGroupController {

    TutorialGroupService tutorialGroupService;

    @Autowired
    TutorialGroupController(TutorialGroupService tutorialGroupService){
        this.tutorialGroupService = tutorialGroupService;
    }

    @GetMapping(value="/list")
    public Page<TutorialGroup> listTutorialGroups(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer itemsPerPage,
                                                  @RequestParam(defaultValue = "") String sortBy, @RequestParam(defaultValue = "false") Boolean sortDesc){
        return tutorialGroupService.getAllTutorialGroupsPaged(page,itemsPerPage,sortBy,sortDesc);
    }

    @GetMapping(value = {"/get/{tutorialGroupId}"})
    public TutorialGroup getTutorialGroup(@PathVariable Long tutorialGroupId) {
        return tutorialGroupService.getTutorialGroupById(tutorialGroupId);
    }

    @PostMapping(value = "/register")
    public TutorialGroup registerTutorialGroup(@Valid @RequestBody TutorialGroup tutorialGroup){
        return tutorialGroupService.registerTutorialGroup(tutorialGroup);
    }

    @PutMapping(value = "/edit/{tutorialGroupId}")
    public TutorialGroup updateTutorialGroup(@Valid @RequestBody TutorialGroup updatedTutorialGroup, @PathVariable Long tutorialGroupId){
        return tutorialGroupService.updateTutorialGroup(updatedTutorialGroup,tutorialGroupId);
    }

    @DeleteMapping(value = {"/delete/{tutorialGroupId}"})
    public void deleteTutorialGroup(@PathVariable Long tutorialGroupId) {
        tutorialGroupService.deleteTutorialGroupById(tutorialGroupId);
    }

    @GetMapping(value="/getbysection/{sectionId}")
    public List<TutorialGroup> getTutorialGroupBySection(@PathVariable Integer sectionId) {
        return tutorialGroupService.getTutorialGroupBySection(sectionId);
    }


}
