package com.sept.backend.repositories;

import com.sept.backend.model.Booking;
import com.sept.backend.model.Business;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BusinessRepository extends CrudRepository<Business, Long> {



    @Override
    Iterable<Business> findAllById(Iterable<Long> iterable);

    @Override
    Optional<Business> findById(Long aLong);
}
