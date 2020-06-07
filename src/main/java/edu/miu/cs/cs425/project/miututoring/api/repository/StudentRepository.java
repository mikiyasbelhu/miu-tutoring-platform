package edu.miu.cs.cs425.project.miututoring.api.repository;

import edu.miu.cs.cs425.project.miututoring.api.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    Page<Student> findAllByStudentNumberContainingOrFirstNameContainingOrMiddleNameContainingOrLastNameContainingOrderByFirstName(String studentNumber, String firstName, String middleName, String lastName,
                                                                                                                                  Pageable pageable);
    List<Student> findAllByCgpaEquals(Double overdueFee);
    List<Student> findAllByEnrollmentDateEquals(LocalDate datePublished);
}
