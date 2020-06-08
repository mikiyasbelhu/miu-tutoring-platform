package edu.miu.cs.cs425.project.miututoring.api.controller;

import edu.miu.cs.cs425.project.miututoring.api.service.FacultyService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

@RestController
@RequestMapping(value = "/api/v1/faculty", produces = MediaType.APPLICATION_JSON_VALUE)
public class FacultryController {

    private FacultyService facultyService;


}
