package com.vtca.color.reader.notification.mail;

import com.vtca.color.reader.consumer.domain.contact.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;
    @Value("${email.sender}")
    private String sender;

    /**
     * Send email with simple info as text
     * @param contact
     */
    public void sendSimpleMessage(Contact contact) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(contact.getSubject());
        email.setFrom(sender);
        email.setTo(contact.getEmail());
        //set welcome content email
        email.setText("Welcome to Color Reader !!!");

        //send mail
        emailSender.send(email);
    }
}
