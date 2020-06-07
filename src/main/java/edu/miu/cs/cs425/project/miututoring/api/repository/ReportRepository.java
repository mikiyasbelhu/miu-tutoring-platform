package edu.miu.cs.cs425.project.miututoring.api.repository;

import edu.miu.cs.cs425.project.miututoring.api.model.Report;
import edu.miu.cs.cs425.project.miututoring.api.model.TutorialGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReportRepository extends CrudRepository<Report, Integer> {
    List<Report> findAllByTutorialGroupEquals(TutorialGroup tutorialGroup);
}
