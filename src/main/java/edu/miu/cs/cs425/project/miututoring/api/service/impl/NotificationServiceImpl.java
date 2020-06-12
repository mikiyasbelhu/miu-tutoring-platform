package edu.miu.cs.cs425.project.miututoring.api.service.impl;

import edu.miu.cs.cs425.project.miututoring.api.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;



@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    JavaMailSender sender;

    @Override
    public void sendNotification(String from, String to, String body, String subject) throws MessagingException {

        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setText(body, true); // Use this or above line.
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setFrom(from);
        sender.send(mimeMessage);
    }

}
