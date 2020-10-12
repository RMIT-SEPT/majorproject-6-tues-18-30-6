package com.sept.backend.services;

import com.sept.backend.model.User;
import com.sept.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveOrUpdateUser(User user){

        if(userRepository.existsById(user.getId())){
         userRepository.deleteById(user.getId());
        }
        return userRepository.save(user);
    }

    public Optional<User> getById(Long id){
        return userRepository.findById(id);
    }

    public User getByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
