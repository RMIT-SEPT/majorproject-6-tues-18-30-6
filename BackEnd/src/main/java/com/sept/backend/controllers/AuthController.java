package com.sept.backend.controllers;


import com.sept.backend.model.Business;
import com.sept.backend.model.User;
import com.sept.backend.model.Role;
import com.sept.backend.payload.JWTLoginSuccessResponse;
import com.sept.backend.payload.LoginRequest;
import com.sept.backend.services.BusinessService;
import com.sept.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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

    @Autowired
    private BusinessService businessService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest login){
        String token = "12345678901234567890";
        User user = userService.getByUsername(login.getUsername());
        if(user != null) {
            return new ResponseEntity<JWTLoginSuccessResponse>(new JWTLoginSuccessResponse(true, token, user), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Error: User not found.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/registerUser")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user){
        User newUser = userService.saveOrUpdateUser(user);

        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

    @PostMapping("/registerBusiness")
    public ResponseEntity<?> registerBusiness(@Valid @RequestBody Business business){
        Business newBusiness = businessService.saveOrUpdateBusiness(business);

        return new ResponseEntity<Business>(newBusiness, HttpStatus.CREATED);
    }
}
