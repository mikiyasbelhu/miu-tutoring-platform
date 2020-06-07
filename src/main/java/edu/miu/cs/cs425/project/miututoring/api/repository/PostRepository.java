package edu.miu.cs.cs425.project.miututoring.api.repository;

import edu.miu.cs.cs425.project.miututoring.api.model.Post;
import edu.miu.cs.cs425.project.miututoring.api.model.TutorialGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post,Long> {
    List<Post> findAllByTutorialGroupEquals(TutorialGroup tutorialGroup);
}
