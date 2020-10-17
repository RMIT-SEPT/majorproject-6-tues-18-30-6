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
        Optional<User> updated = userRepository.findById(user.getId());
        if(updated.isPresent()){
            updated.get().setUsername(user.getUsername());
            updated.get().setPassword(user.getPassword());
            updated.get().setPhoneNumber(user.getPhoneNumber());
            updated.get().setEmail(user.getEmail());
            updated.get().setAddress(user.getAddress());
            updated.get().setRoles(user.getRoles());

            return userRepository.save(updated.get());
        } else {
            return userRepository.save(user);
        }
    }

    public Optional<User> getById(Long id){
        return userRepository.findById(id);
    }

    public User getByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
