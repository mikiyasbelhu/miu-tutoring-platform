package edu.miu.cs.cs425.project.miututoring.api.service;

import edu.miu.cs.cs425.project.miututoring.api.model.Post;
import edu.miu.cs.cs425.project.miututoring.api.model.TutorialGroup;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();
    List<Post> getPostsByTutorialGroup(TutorialGroup tutorialGroup);
    Post savePost(Post post);
    Post updatePost(Post updatedPost, Long postId);
    void deletePostById(Long postId);
}
