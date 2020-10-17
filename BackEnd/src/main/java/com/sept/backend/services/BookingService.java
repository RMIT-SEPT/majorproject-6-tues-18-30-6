package com.sept.backend.services;

import com.sept.backend.model.Booking;
import com.sept.backend.model.User;
import com.sept.backend.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public boolean deleteBooking(Booking booking){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        if(booking.getTime().getStartTime().after(calendar.getTime())){
            return bookingRepository.deleteBookingById(booking.getId());
        } else {
            return false;
        }

    }

    public Booking saveBooking(Booking booking){
        return bookingRepository.save(booking);
    }

    public List<Booking> getBookingHistory(User user){
        List<Booking> bookings = new ArrayList<>();
        Date now = new Date();
        for(Booking b : bookingRepository.getBookingsByCustomer(user)){
            if(b.getTime().getStartTime().before(now)){
                bookings.add(b);
            }
        }

        return bookings;
    }

    public List<Booking> getBookingsByUser(User user){
        List<Booking> bookings = new ArrayList<>();
        Date now = new Date();
        for(Booking b : bookingRepository.getBookingsByCustomer(user)){
            if(b.getTime().getStartTime().after(now)){
                bookings.add(b);
            }
        }
        return bookings;
    }


}
