package com.sept.backend.repositories;

import com.sept.backend.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    Iterable<User> findAllById(Iterable<Long> iterable);

    User findByUsername(String username);

    void deleteById(Long id);
}
