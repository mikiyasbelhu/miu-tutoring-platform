package edu.miu.cs.cs425.project.miututoring.api.repository;

import edu.miu.cs.cs425.project.miututoring.api.model.Faculty;
import edu.miu.cs.cs425.project.miututoring.api.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty,Long> {
    Page<Faculty> findAllByDepartmentContainingOrFirstNameContainingOrMiddleNameContainingOrLastNameContainingOrderByFirstName(String department, String firstName, String middleName, String lastName,
                                                                                                                              Pageable pageable);
    Optional<Faculty> findFacultyByDepartment(String department);

    Optional<Student> findByUsername(String username);

}