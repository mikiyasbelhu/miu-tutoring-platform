package edu.miu.cs.cs425.project.miututoring.api.service.impl;

import edu.miu.cs.cs425.project.miututoring.api.repository.FacultyRepository;
import edu.miu.cs.cs425.project.miututoring.api.service.FacultyService;
import org.springframework.stereotype.Service;

@Service
public class FacultyServiceImpl implements FacultyService {

    FacultyRepository facultyRepository;
}
