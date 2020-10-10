package com.sept.backend.payload;

import com.sept.backend.model.User;
import com.sept.backend.model.Role;

import java.util.List;

public class JWTLoginSuccessResponse {
    private boolean success;
    private String token;
    private String username;
    private Long id;
    private String email;
    private List<Role> roles;

    public JWTLoginSuccessResponse(boolean success, String token, User user) {
        this.success = success;
        this.token = token;
        this.username = user.getUsername();
        this.id = user.getId();
        this.email = user.getEmail();
        this.roles = user.getRoles();
    }

    public boolean isSuccess() {
        return success;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        return "JWTLoginSuccessResponse{" +
                "success=" + success +
                ", token='" + token + '\'' +
                ", username='" + username + '\'' +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }
}
