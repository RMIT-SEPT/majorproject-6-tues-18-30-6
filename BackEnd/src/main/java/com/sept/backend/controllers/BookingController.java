package com.sept.backend.controllers;


import com.sept.backend.model.Booking;
import com.sept.backend.model.Business;
import com.sept.backend.model.Shift;
import com.sept.backend.model.User;
import com.sept.backend.repositories.BusinessRepository;
import com.sept.backend.services.BookingService;
import com.sept.backend.services.BusinessService;
import com.sept.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    BusinessService businessService;

    @Autowired
    UserService userService;

    @Autowired
    BookingService bookingService;

    @RequestMapping("/businesses")
    public ResponseEntity<List<Business>> getAllBusinesses(){
        return new ResponseEntity<List<Business>>(businessService.getAll(), HttpStatus.OK);
    }

    @RequestMapping("/available")
    public ResponseEntity<?> getAvailableBookings(@RequestBody Long businessId){
        Optional<Business> opt = businessService.getBusinessById(businessId);
        if(opt.isPresent()){
            Business business= opt.get();
            return new ResponseEntity<List<Shift>>(business.getAvailableBookings(), HttpStatus.OK);
        }

        return new ResponseEntity<String>("Error: Could not find business", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping("/history")
    public ResponseEntity<?> getUserHistory(Long custId){
        if(userService.getById(custId).isPresent()){
            List<Booking> bookings = bookingService.getBookingsByUser(userService.getById(custId).get());
            return new ResponseEntity<List<Booking>>(bookings, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Error: Could not find user", HttpStatus.BAD_REQUEST);
    }
}
