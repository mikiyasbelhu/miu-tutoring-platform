package edu.miu.cs.cs425.project.miututoring.api.service.impl;

import edu.miu.cs.cs425.project.miututoring.api.model.Faculty;
import edu.miu.cs.cs425.project.miututoring.api.repository.FacultyRepository;
import edu.miu.cs.cs425.project.miututoring.api.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {

    FacultyRepository facultyRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    FacultyServiceImpl(FacultyRepository facultyRepository){
        this.facultyRepository = facultyRepository;
    }

    @Override
    public List<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }

    @Override
    public Page<Faculty> getAllFacultyPaged(int pageNo, Integer pageSize, String sortBy, Boolean sortDesc) {
        return facultyRepository.findAll(PageRequest.of(pageNo, pageSize == -1 ? Integer.MAX_VALUE :pageSize, !sortBy.equals("") ? Sort.by(sortDesc?Sort.Direction.DESC :Sort.Direction.ASC, sortBy): Sort.unsorted()));
        
    }

    @Override
    public Faculty getFacultyById(Long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    @Override
    public Faculty registerFaculty(Faculty faculty) {
         if(!facultyRepository.findByUsername(faculty.getUsername()).isPresent()){
             faculty.setPassword(this.passwordEncoder.encode(faculty.getPassword()));
            return facultyRepository.save(faculty);
        }
        return null;
    }

    @Override
    public Faculty updateFaculty(Faculty updateFaculty, Long facultyId) {
        return facultyRepository.findById(facultyId)
                .map(faculty -> {
                    faculty.setDepartment(updateFaculty.getDepartment());
                    faculty.setFirstName(updateFaculty.getFirstName());
                    faculty.setMiddleName(updateFaculty.getMiddleName());
                    faculty.setLastName(updateFaculty.getLastName());
                    return facultyRepository.save(faculty);
                })
                .orElseGet(() -> facultyRepository.save(updateFaculty));
    }

    @Override
    public void deleteFacultyById(Long facultyId) {
        facultyRepository.deleteById(facultyId);

    }

    @Override
    public Page<Faculty> searchFaculty(String searchQuery, int pageNo, Integer pageSize, String sortBy, Boolean sortDesc) {
        return facultyRepository.findAllByDepartmentContainingOrFirstNameContainingOrMiddleNameContainingOrLastNameContainingOrderByFirstName(searchQuery, searchQuery, searchQuery, searchQuery,PageRequest.of(pageNo,pageSize,Sort.by(sortDesc?Sort.Direction.DESC : Sort.Direction.ASC, sortBy)));
    }
}
