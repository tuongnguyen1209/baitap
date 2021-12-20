package com.vtca.color.reader.consumer.domain.contact;

import com.vtca.color.reader.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/consumer")
public class ContactController {

    @Autowired
    private ContactService emailService;

    @PostMapping(value = "/contact")
    public Response sendEmail(@RequestBody Contact contact) {
        emailService.save(contact);
        return Response.ok();
    }
}
