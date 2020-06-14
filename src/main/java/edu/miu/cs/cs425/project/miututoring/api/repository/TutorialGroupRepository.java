package edu.miu.cs.cs425.project.miututoring.api.repository;

import edu.miu.cs.cs425.project.miututoring.api.model.TutorialGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutorialGroupRepository extends JpaRepository<TutorialGroup,Long> {
    List<TutorialGroup> findAllBySection_SectionIdEquals(Integer sectionId);
}
