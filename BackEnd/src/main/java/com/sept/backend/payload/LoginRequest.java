package com.sept.backend.payload;

import javax.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank(message =  "Must include a username")
    private String username;

    @NotBlank(message = "Must include a password")
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
