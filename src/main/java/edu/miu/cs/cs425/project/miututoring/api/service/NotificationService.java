package edu.miu.cs.cs425.project.miututoring.api.service;

import javax.mail.MessagingException;

public interface NotificationService {
    void sendNotification(String from, String to, String body, String subject) throws MessagingException;
}
