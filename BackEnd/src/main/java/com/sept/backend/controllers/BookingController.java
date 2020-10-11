package com.sept.backend.controllers;


import com.sept.backend.model.Booking;
import com.sept.backend.model.Business;
import com.sept.backend.model.Shift;
import com.sept.backend.model.User;
import com.sept.backend.services.BookingService;
import com.sept.backend.services.BusinessService;
import com.sept.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
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

    @RequestMapping("/getAllBusinesses")
    public ResponseEntity<List<Business>> getAllBusinesses(){
        return new ResponseEntity<List<Business>>(businessService.getAll(), HttpStatus.OK);
    }

    @RequestMapping("/getAvailableBookings")
    public ResponseEntity<?> getAvailableBookings(@Valid @RequestBody Long businessId){
        Optional<Business> opt = businessService.getBusinessById(businessId);
        if(opt.isPresent()){
            Business business= opt.get();
            return new ResponseEntity<List<Shift>>(business.getAvailableBookings(), HttpStatus.OK);
        }

        return new ResponseEntity<String>("Error: Could not find business", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping("/getEmployeeAvailability")
    public ResponseEntity<?> getEmployeeAvailability(Long businessId){
        Optional<Business> business = businessService.getBusinessById(businessId);
        if(business.isPresent()){
            return new ResponseEntity<List<Shift>>(business.get().getEmployeeAvailability(), HttpStatus.OK);
        }
        return new ResponseEntity<String>("Error: Could not find business", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping("/setAvailableBookings")
    public ResponseEntity<?> getEmployeeAvailability(Long businessId, List<Shift> availability){
        Optional<Business> business = businessService.getBusinessById(businessId);
        if(business.isPresent()){
            business.get().setAvailableBookings(availability);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<String>("Error: Could not find business", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(path = "/setAvailability")
    public ResponseEntity<?> setAvailability(Long businessId, List<Shift> availability){
        Optional<Business> business = businessService.getBusinessById(businessId);
        if(business.isPresent()){
            business.get().addEmployeeAvailability(availability);
            return new ResponseEntity<String>("Availability Updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Error: Availability could not be set", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/getAdminBookings")
    public ResponseEntity<?> retrieveBusinessBookings(Long businessId){
        Optional<Business> business = businessService.getBusinessById(businessId);
        if(business.isPresent()){
            List<Booking> bookings = business.get().getBookings();
            return new ResponseEntity<List<Booking>>(bookings, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Could not retrieve bookings for this business", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping("/history")
    public ResponseEntity<?> getUserHistory(@Valid @RequestBody Long custId){
        if(userService.getById(custId).isPresent()){
            List<Booking> bookings = bookingService.getBookingHistory(userService.getById(custId).get());
            return new ResponseEntity<List<Booking>>(bookings, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Error: Could not find user", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping("/current")
    public ResponseEntity<?> getCurrentBookings(@Valid @RequestBody Long custId){
        if(userService.getById(custId).isPresent()){
            List<Booking> bookings = bookingService.getBookingsByUser(userService.getById(custId).get());
            return new ResponseEntity<List<Booking>>(bookings, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Error: Could not find user", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping("/book")
    public ResponseEntity<?> book(@Valid @RequestBody Long businessId, @Valid @RequestBody Booking booking){
        Optional<Business> business = businessService.getBusinessById(businessId);
        if(business.isPresent()){
            business.get().Book(booking);
            if(bookingService.saveBooking(booking) != null) {
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
            return new ResponseEntity<String>("Error: Could not create booking",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("Error: Could not find business",HttpStatus.BAD_REQUEST);
    }



    @RequestMapping("/cancel")
    public ResponseEntity<?> deleteBooking(@Valid @RequestBody Booking booking){
        if(bookingService.deleteBooking(booking)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<String>("Error: Could not delete booking", HttpStatus.BAD_REQUEST);
    }
}
