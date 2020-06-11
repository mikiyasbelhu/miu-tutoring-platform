package edu.miu.cs.cs425.project.miututoring.api.controller;

import edu.miu.cs.cs425.project.miututoring.api.model.Post;
import edu.miu.cs.cs425.project.miututoring.api.model.TutorialGroup;
import edu.miu.cs.cs425.project.miututoring.api.model.User;
import edu.miu.cs.cs425.project.miututoring.api.service.MyUserDetailsService;
import edu.miu.cs.cs425.project.miututoring.api.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpSession;
import org.springframework.messaging.simp.user.SimpSubscription;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/v1/post", produces = MediaType.APPLICATION_JSON_VALUE)
public class PostController {

    private SimpMessagingTemplate simpMessagingTemplate;
    private PostService postService;
    private SimpUserRegistry simpUserRegistry;
    private MyUserDetailsService userDetailsService;

    @Autowired
    public PostController(SimpMessagingTemplate template, PostService postService, SimpUserRegistry simpUserRegistry, MyUserDetailsService userDetailsService) {
        this.simpMessagingTemplate = template;
        this.postService = postService;
        this.simpUserRegistry = simpUserRegistry;
        this.userDetailsService = userDetailsService;
    }

    @MessageMapping("/chat.register/{tutorialGroupId}")
    public void register(@Payload Post chatMessage, @DestinationVariable String tutorialGroupId, SimpMessageHeaderAccessor headerAccessor){
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        if(chatMessage.getType() == Post.MessageType.CHAT){
            chatMessage = this.postService.savePost(chatMessage);
        }
        this.simpMessagingTemplate.convertAndSend("/group/"+tutorialGroupId, chatMessage);
    }

    @MessageMapping("/chat.send/{tutorialGroupId}")
    public void sendMessage(@Payload Post chatMessage, @DestinationVariable String tutorialGroupId){
        if(chatMessage.getType() == Post.MessageType.CHAT){
            chatMessage = this.postService.savePost(chatMessage);
        }
        this.simpMessagingTemplate.convertAndSend("/group/"+tutorialGroupId, chatMessage);
    }

    @MessageMapping("/chat.language/{tutorialGroupId}")
    public void sendLanguage(@Payload Post chatMessage, @DestinationVariable String tutorialGroupId){
        this.simpMessagingTemplate.convertAndSend("/group/"+tutorialGroupId+"/language", chatMessage);
    }

    @MessageMapping("/chat.stream/{tutorialGroupId}")
    public void streamMessage(@Payload Post chatMessage, @DestinationVariable String tutorialGroupId){
        this.simpMessagingTemplate.convertAndSend("/group/"+tutorialGroupId+"/code", chatMessage);
    }

    @PostMapping(value="/getbytutorialgroup")
    public List<Post> listPosts(@Valid @RequestBody TutorialGroup tutorialGroup){
        return postService.getPostsByTutorialGroup(tutorialGroup);
    }

    @GetMapping(value="/group/{tutorialGroupId}/users")
    public List<User> getOnlineUsers(@PathVariable Integer tutorialGroupId) {
        Set<SimpUser> userRegistries = simpUserRegistry.getUsers();
        Set<User> onlineUsers = new HashSet<>();
        for(SimpUser user:userRegistries){
            for(SimpSession session:user.getSessions()){
                for(SimpSubscription subscription:session.getSubscriptions()){
                    if(subscription.getDestination().equals("/group/"+tutorialGroupId)){
                        onlineUsers.add(userDetailsService.getByUsername(user.getName()));
                    }
                }
            }
        }
        List<User> users = new ArrayList<>();
        users.addAll(onlineUsers);
        return users;
    }

}
