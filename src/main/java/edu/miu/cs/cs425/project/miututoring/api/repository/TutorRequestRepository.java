package edu.miu.cs.cs425.project.miututoring.api.repository;

import edu.miu.cs.cs425.project.miututoring.api.model.TutorRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRequestRepository extends JpaRepository<TutorRequest, Integer> {
    Page<TutorRequest> findAllByEnrollment_Student_IdEquals(Long studentId, Pageable pageable);
}
