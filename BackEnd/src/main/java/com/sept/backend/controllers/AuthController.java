package com.sept.backend.controllers;


import com.sept.backend.model.Business;
import com.sept.backend.model.User;
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
            return new ResponseEntity<>(new JWTLoginSuccessResponse(true, token, user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error: User not found.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/registerUser")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user){
        if(userService.getByUsername(user.getUsername()) != null){
            return new ResponseEntity<>("Error: User already exists", HttpStatus.BAD_REQUEST);
        }
        User newUser = userService.saveOrUpdateUser(user);

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PostMapping("/updateUser")
    public ResponseEntity<?> updateUser(@Valid @RequestBody User user){
        if(userService.getByUsername(user.getUsername()) == null){
            return new ResponseEntity<>("Error: User does not exist", HttpStatus.BAD_REQUEST);
        }
        User newUser = userService.saveOrUpdateUser(user);

        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @PostMapping("/getDetails")
    public ResponseEntity<?> getUserDetails(@Valid @RequestBody String username){
        User user = userService.getByUsername(username);
        if(user == null){
            return new ResponseEntity<>("Error: User does not exist", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/registerBusiness")
    public ResponseEntity<?> registerBusiness(@Valid @RequestBody Business business){
        Business newBusiness = businessService.saveOrUpdateBusiness(business);

        return new ResponseEntity<>(newBusiness, HttpStatus.CREATED);
    }
}
