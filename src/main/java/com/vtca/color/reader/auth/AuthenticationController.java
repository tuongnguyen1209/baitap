package com.vtca.color.reader.auth;

import com.vtca.color.reader.auth.user.UserDetailsCustomService;
import com.vtca.color.reader.auth.jwt.JwtUtil;
import com.vtca.color.reader.auth.user.User;
import com.vtca.color.reader.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsCustomService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;


    @PostMapping(value = "/authenticate")
    public Response createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {

        //throw DisabledException or BadCredentialsException
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        //get user info by username to check whether it's existing on system or not
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        //generate token info
        final String token = jwtTokenUtil.generateToken(userDetails);

        return Response.ok(new AuthenticationResponse(token));
    }

    @PostMapping(value = "/register")
    public Response saveUser(@RequestBody User user) {
        return Response.ok(userDetailsService.save(user));
    }
}
