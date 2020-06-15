package edu.miu.cs.cs425.project.miututoring.api.controller;

import edu.miu.cs.cs425.project.miututoring.api.model.Faculty;
import edu.miu.cs.cs425.project.miututoring.api.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;

@RestController
@RequestMapping(value = "/api/v1/faculty", produces = MediaType.APPLICATION_JSON_VALUE)
public class FacultyController {

    private FacultyService facultyService;

    @Autowired
    FacultyController(FacultyService facultyService){
        this.facultyService = facultyService;
    }

    @GetMapping(value = "/list")
    public Page<Faculty> listFaculty(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer itemsPerPage, @RequestParam(defaultValue = "") String sortBy, @RequestParam(defaultValue = "false") Boolean sortDesc){
        return facultyService.getAllFacultyPaged(page,itemsPerPage,sortBy,sortDesc);
    }

    @GetMapping(value = {"/get/{facultyId}"})
    public Faculty getFaculty(@PathVariable Long facultyId){
        return facultyService.getFacultyById(facultyId);
    }
    @PostMapping(value = "/register")
    public Faculty registerFaculty(@Valid @RequestBody Faculty faculty) throws Exception {
        faculty.setRoles(new ArrayList<>(Arrays.asList("ROLE_FACULTY")));
        return facultyService.registerFaculty(faculty);
    }

    @PutMapping(value = {"/edit/{facultyId}"})
    public Faculty updateFaculty(@Valid @RequestBody Faculty updateFaculty, @PathVariable Long facultyId){
        return facultyService.updateFaculty(updateFaculty,facultyId);
    }

    @DeleteMapping(value = {"/delete/{facultyId}"})
    public void deleteFaculty(@PathVariable Long facultyId){
        facultyService.deleteFacultyById(facultyId);
    }

    @GetMapping(value = "/search")
    public Page<Faculty> searchFaculty(@RequestParam String searchQuery, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer itemsPerPage,
                                       @RequestParam String sortBy, @RequestParam(defaultValue = "false")Boolean sortDesc){
        return facultyService.searchFaculty(searchQuery,page,itemsPerPage,sortBy,sortDesc);
    }




}
