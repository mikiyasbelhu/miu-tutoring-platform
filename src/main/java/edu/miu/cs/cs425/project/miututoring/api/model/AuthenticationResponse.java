package edu.miu.cs.cs425.project.miututoring.api.model;

import java.io.Serializable;
import java.util.List;

public class AuthenticationResponse implements Serializable {

    private final String jwt;
    private final String username;
    private final List<String> roles;
    private final String name;

    public AuthenticationResponse(String jwt, String username, String name, List<String> roles) {
        this.jwt = jwt;
        this.username = username;
        this.name = name;
        this.roles = roles;
    }

    public String getJwt() {
        return jwt;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public List<String> getRoles() {
        return roles;
    }


}