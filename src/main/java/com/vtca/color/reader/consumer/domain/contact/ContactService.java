package com.vtca.color.reader.consumer.domain.contact;

import com.vtca.color.reader.common.logger.LoggerUtils;
import com.vtca.color.reader.notification.mail.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ContactService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private ContactRepository repository;

    /**
     * Store contact info and Send email with simple info as text
     * @param contact
     */
    public void save(Contact contact) {

        ContactValidator.validate(contact);

        contact.setCreatedBy("SYSTEM");
        contact.setCreatedDate(LocalDateTime.now());
        contact.setLastUpdateBy("SYSTEM");
        contact.setLastUpdateDate(LocalDateTime.now());
        //store contact client to DB
        repository.save(contact);
        try {
            //send mail
            emailService.sendSimpleMessage(contact);
        } catch (Exception e) {
            LoggerUtils.warning("There is an issue on sending mail with error", e);
        }
    }
}
