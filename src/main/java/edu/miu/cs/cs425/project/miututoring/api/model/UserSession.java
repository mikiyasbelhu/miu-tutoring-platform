package edu.miu.cs.cs425.project.miututoring.api.model;

import java.security.Principal;

public final class UserSession implements Principal {

    private final String name;

    public UserSession(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}