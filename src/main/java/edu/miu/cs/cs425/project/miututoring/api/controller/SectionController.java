package edu.miu.cs.cs425.project.miututoring.api.controller;

import edu.miu.cs.cs425.project.miututoring.api.model.Section;
import edu.miu.cs.cs425.project.miututoring.api.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/section",produces = MediaType.APPLICATION_JSON_VALUE)
public class SectionController {
     SectionService sectionService;
    @Autowired
    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }
    @GetMapping(value="/list")
    public List<Section>listOfSections(){
        return sectionService.getAllSections();
    }
    @RequestMapping(value="/get/{sectionId}")
    public Section getSection(@PathVariable Integer sectionId){
        return sectionService.getSectionById(sectionId);
    }

    @PutMapping(value="/edit/{sectionId}")
    public Section updateSection(@Valid @RequestBody Section section,@PathVariable Integer sectionId){
        return sectionService.updateSection(section,sectionId);
    }

    @PostMapping(value="/register")
    public Section registerSection(@Valid @RequestBody Section section){
        return sectionService.registerSection(section);
    }

    @DeleteMapping(value="/delete/{sectionId}")
    public void deleteSection(@PathVariable Integer sectionId){
        sectionService.deleteSectionById(sectionId);
    }
}
