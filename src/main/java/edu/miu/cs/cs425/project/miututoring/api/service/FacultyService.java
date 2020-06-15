package edu.miu.cs.cs425.project.miututoring.api.service;

import edu.miu.cs.cs425.project.miututoring.api.model.Faculty;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FacultyService {

    List<Faculty> getAllFaculty();
    Page<Faculty> getAllFacultyPaged(int pageNo, Integer pageSize,String sortBy, Boolean sortDesc);
    Faculty getFacultyById(Long id);
    Faculty registerFaculty(Faculty faculty) throws Exception;
    Faculty updateFaculty(Faculty updateFaculty, Long facultyId);
    void deleteFacultyById(Long facultyId);
    Page<Faculty> searchFaculty(String searchQuery, int pageNo, Integer pageSize, String sortBy, Boolean sortDesc);
    Faculty getByUsername(String username);
}
