package edu.miu.cs.cs425.project.miututoring.api.service.impl;

import edu.miu.cs.cs425.project.miututoring.api.model.Post;
import edu.miu.cs.cs425.project.miututoring.api.model.TutorialGroup;
import edu.miu.cs.cs425.project.miututoring.api.repository.PostRepository;
import edu.miu.cs.cs425.project.miututoring.api.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    PostRepository postRepository;

    @Autowired
    PostServiceImpl(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAllPosts() {
        return (List<Post>)postRepository.findAll();
    }

    @Override
    public List<Post> getPostsByTutorialGroup(TutorialGroup tutorialGroup) {
        return postRepository.findAllByTutorialGroupEquals(tutorialGroup);
    }

    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(Post updatedPost, Long postId) {
        return postRepository.findById(postId)
                .map(post -> {
                    return postRepository.save(post);
                })
                .orElseGet(() -> postRepository.save(updatedPost));
    }

    @Override
    public void deletePostById(Long postId) {
        postRepository.deleteById(postId);
    }

}
