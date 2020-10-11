package com.sept.backend.repositories;

import com.sept.backend.model.Booking;
import com.sept.backend.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {

    public Iterable<Booking> getBookingByCustomer(User user);

}
