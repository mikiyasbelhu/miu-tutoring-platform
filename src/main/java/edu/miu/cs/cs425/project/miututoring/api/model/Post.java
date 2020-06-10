package edu.miu.cs.cs425.project.miututoring.api.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Post {

    public enum MessageType{
        CHAT,LEAVE,JOIN,LANGUAGE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private String sender;
    private MessageType type;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "tutorialGroupId", nullable = false)
    @NotNull(message = "*tutorialGroup is required")
    private TutorialGroup tutorialGroup;

    @Column(name = "created_on")
    @CreationTimestamp
    private LocalDateTime createdOn;

    public Post() {
    }

    public Post(String content, String sender, MessageType type, TutorialGroup tutorialGroup) {
        this.content = content;
        this.sender = sender;
        this.type = type;
        this.tutorialGroup = tutorialGroup;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public TutorialGroup getTutorialGroup() {
        return tutorialGroup;
    }

    public void setTutorialGroup(TutorialGroup tutorialGroup) {
        this.tutorialGroup = tutorialGroup;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", sender='" + sender + '\'' +
                ", type=" + type +
                ", createdOn=" + createdOn +
                '}';
    }
}
