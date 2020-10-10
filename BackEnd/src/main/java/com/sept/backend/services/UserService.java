package com.sept.backend.services;

import com.sept.backend.model.User;
import com.sept.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveOrUpdateUser(User user){



        return userRepository.save(user);
    }
}
