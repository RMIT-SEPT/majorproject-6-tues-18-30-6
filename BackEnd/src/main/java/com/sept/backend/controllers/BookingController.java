package com.sept.backend.controllers;


import com.sept.backend.model.Booking;
import com.sept.backend.model.Business;
import com.sept.backend.model.Shift;
import com.sept.backend.model.User;
import com.sept.backend.payload.AvailabilityRequest;
import com.sept.backend.payload.BookingRequest;
import com.sept.backend.payload.ErrorResponse;
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
        return new ResponseEntity<>(businessService.getAll(), HttpStatus.OK);
    }

    @RequestMapping("/getAvailableBookings")
    public ResponseEntity<?> getAvailableBookings(@Valid @RequestBody Business business){
        Optional<Business> opt = businessService.getBusinessById(business.getId());
        if(opt.isPresent()){
            return new ResponseEntity<>(opt.get().getAvailableBookings(), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ErrorResponse("Error: Could not find business"), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping("/getEmployeeAvailability")
    public ResponseEntity<?> getEmployeeAvailability(@Valid @RequestBody Business business){
        Optional<Business> opt = businessService.getBusinessById(business.getId());
        if(opt.isPresent()){
            return new ResponseEntity<>(opt.get().getEmployeeAvailability(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorResponse("Error: Could not find business"), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping("/setAvailableBookings")
    public ResponseEntity<?> setAvailableBookings(@Valid @RequestBody AvailabilityRequest request){
        Optional<Business> business = businessService.getBusinessById(request.getBusinessId());
        if(business.isPresent()){
            business.get().setAvailableBookings(request.getAvailability());
            businessService.saveOrUpdateBusiness(business.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorResponse("Error: Could not find business"), HttpStatus.BAD_REQUEST);
    }

    @PostMapping(path = "/setAvailability")
    public ResponseEntity<?> setAvailability(@Valid @RequestBody AvailabilityRequest request){
        Optional<Business> business = businessService.getBusinessById(request.getBusinessId());
        if(business.isPresent()){
            business.get().addEmployeeAvailability(request.getAvailability());
            businessService.saveOrUpdateBusiness(business.get());
            return new ResponseEntity<>("Availability Updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ErrorResponse("Error: Availability could not be set"), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/getAdminBookings")
    public ResponseEntity<?> retrieveBusinessBookings(@Valid @RequestBody Business business){
        Optional<Business> opt = businessService.getBusinessById(business.getId());
        if(opt.isPresent()){
            List<Booking> bookings = opt.get().getBookings();
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorResponse("Error: Could not retrieve bookings for this business"), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping("/history")
    public ResponseEntity<?> getUserHistory(@Valid @RequestBody User user){
        if(userService.getByUsername(user.getUsername()) != null){
            List<Booking> bookings = bookingService.getBookingHistory(userService.getByUsername(user.getUsername()));
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorResponse("Error: Could not find user"), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping("/current")
    public ResponseEntity<?> getCurrentBookings(@Valid @RequestBody User user){
        if(userService.getByUsername(user.getUsername())!=null){
            List<Booking> bookings = bookingService.getBookingsByUser(userService.getByUsername(user.getUsername()));
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorResponse("Error: Could not find user"), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping("/book")
    public ResponseEntity<?> book(@Valid @RequestBody BookingRequest request){
        Optional<Business> business = businessService.getBusinessById(request.getBusinessId());
        if(business.isPresent()){
            if(bookingService.saveBooking(request.getBooking()) != null) {
                business.get().Book(request.getBooking());
                businessService.saveOrUpdateBusiness(business.get());
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
            return new ResponseEntity<>(new ErrorResponse("Error: Could not create booking"),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ErrorResponse("Error: Could not find business"),HttpStatus.BAD_REQUEST);
    }



    @RequestMapping("/cancel")
    public ResponseEntity<?> deleteBooking(@Valid @RequestBody Booking booking){
        if(bookingService.deleteBooking(booking)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorResponse("Error: Could not delete booking"), HttpStatus.BAD_REQUEST);
    }
}
