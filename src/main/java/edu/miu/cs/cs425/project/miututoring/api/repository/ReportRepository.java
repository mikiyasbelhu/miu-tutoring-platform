package edu.miu.cs.cs425.project.miututoring.api.repository;

import edu.miu.cs.cs425.project.miututoring.api.model.Course;
import edu.miu.cs.cs425.project.miututoring.api.model.Report;
import edu.miu.cs.cs425.project.miututoring.api.model.Student;
import edu.miu.cs.cs425.project.miututoring.api.model.TutorialGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {
    List<Report> findAllByStudent_Id(Long studentId);
    List<Report> findAllByTutorialGroup_TutorialGroupId(long tutorialGroup);
    Page<Report> findAllByReportTextContainingOrStudent_FirstNameContainingOrCourse_CourseNameContainingOrTutorialGroup_TutorialGroupNumberContainingOrderByReportText(String searchQuery, String student, String course, String tutorialGroup, Pageable pageable);
}
