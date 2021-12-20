package com.vtca.color.reader.consumer.domain.user;

import com.vtca.color.reader.auth.user.User;
import com.vtca.color.reader.auth.user.UserDetailsCustomService;
import com.vtca.color.reader.notification.mail.EmailService;
import com.vtca.color.reader.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private EmailService gmailService;

    @Autowired
    private UserDetailsCustomService userDetailsService;

    @GetMapping(value = "/profile")
    public Response getUserProfile() {
        return Response.ok(userDetailsService.getUserProfile(request.getHeader("Authorization")));
    }

    @PutMapping("/profile/{id}")
    public Response updateUserProfile(@RequestBody User user, @PathVariable Long id) {
        userDetailsService.update(user, id);
        return Response.ok();
    }
}
