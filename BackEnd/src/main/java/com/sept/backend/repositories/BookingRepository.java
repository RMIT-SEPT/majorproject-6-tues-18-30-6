package com.sept.backend.repositories;

import com.sept.backend.model.Booking;
import com.sept.backend.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {

    public boolean deleteBookingById(Long Id);

    public Iterable<Booking> getBookingsByCustomer(User user);

}
