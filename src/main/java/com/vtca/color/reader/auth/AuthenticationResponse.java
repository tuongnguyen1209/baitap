package com.vtca.color.reader.auth;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthenticationResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;

    public AuthenticationResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }
}
