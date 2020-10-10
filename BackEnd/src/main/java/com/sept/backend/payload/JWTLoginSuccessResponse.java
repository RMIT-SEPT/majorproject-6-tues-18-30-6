package com.sept.backend.payload;

import com.sept.backend.model.User;
import com.sept.backend.model.UserType;

import java.util.List;

public class JWTLoginSuccessResponse {
    private boolean success;
    private String token;
    private String username;
    private Long id;
    private String email;
    private List<UserType> roles;

    public JWTLoginSuccessResponse(boolean success, String token, User user) {
        this.success = success;
        this.token = token;
        this.username = user.getUsername();
        this.id = user.getId();
        this.email = user.getEmail();
        this.roles = user.getRoles();
    }
}
