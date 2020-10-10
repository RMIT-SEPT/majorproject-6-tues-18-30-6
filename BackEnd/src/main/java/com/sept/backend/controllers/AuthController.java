package com.sept.backend.controllers;


import com.sept.backend.model.User;
import com.sept.backend.model.Role;
import com.sept.backend.payload.JWTLoginSuccessResponse;
import com.sept.backend.payload.LoginRequest;
import com.sept.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest login){
        String token = "12345678901234567890";
        User user = new User();
        user.setId(1);
        user.setEmail("test@gmail.com");
        List<Role> roles= new ArrayList<Role>();
        roles.add(Role.CUSTOMER);
        roles.add(Role.WORKER);
        roles.add(Role.ADMIN);
        user.setRoles(roles);
        user.setUsername("Sami");
        return new ResponseEntity<JWTLoginSuccessResponse>(new JWTLoginSuccessResponse(true, token, user), HttpStatus.OK);
    }

    @RequestMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user){


        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }
}
