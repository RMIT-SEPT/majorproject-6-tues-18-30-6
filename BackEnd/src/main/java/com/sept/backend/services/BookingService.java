package com.sept.backend.services;

import com.sept.backend.model.Booking;
import com.sept.backend.model.User;
import com.sept.backend.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;


    public List<Booking> getBookingsByUser(User user){
        List<Booking> bookings = new ArrayList<Booking>();
        bookingRepository.getBookingByCustomer(user).forEach(b -> bookings.add(b));
        return bookings;
    }
}
