package edu.miu.cs.cs425.project.miututoring.api.controller;

import edu.miu.cs.cs425.project.miututoring.api.model.Post;
import edu.miu.cs.cs425.project.miututoring.api.model.TutorialGroup;
import edu.miu.cs.cs425.project.miututoring.api.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/post", produces = MediaType.APPLICATION_JSON_VALUE)
public class PostController {

    private SimpMessagingTemplate simpMessagingTemplate;
    private PostService postService;

    @Autowired
    public PostController(SimpMessagingTemplate template, PostService postService) {
        this.simpMessagingTemplate = template;
        this.postService = postService;
    }

    @MessageMapping("/chat.register/{tutorialGroupId}")
    public void register(@Payload Post chatMessage, @DestinationVariable String tutorialGroupId, SimpMessageHeaderAccessor headerAccessor){
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        Post savedChatMessage = this.postService.savePost(chatMessage);
        System.out.println(savedChatMessage);
        this.simpMessagingTemplate.convertAndSend("/group/"+tutorialGroupId, savedChatMessage);
    }

    @MessageMapping("/chat.send/{tutorialGroupId}")
    public void sendMessage(@Payload Post chatMessage, @DestinationVariable String tutorialGroupId){
        Post savedChatMessage = this.postService.savePost(chatMessage);
        System.out.println(savedChatMessage);
        this.simpMessagingTemplate.convertAndSend("/group/"+tutorialGroupId, savedChatMessage);
    }

    @PostMapping(value="/getbytutorialgroup")
    public List<Post> listPosts(@Valid @RequestBody TutorialGroup tutorialGroup){
        return postService.getPostsByTutorialGroup(tutorialGroup);
    }

}
