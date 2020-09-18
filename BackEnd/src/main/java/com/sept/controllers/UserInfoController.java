package com.sept.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sept.models.Booking;
import com.sept.models.User;
import com.sept.repository.UserRepository;
import com.sept.security.services.UserDetailsServiceImpl;

@RestController
public class UserInfoController {
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	//this mapping value should be changed to the same as forntend's axios request value 
	@PostMapping("/api/updateUserDetail")
	public void updateUserDetail(@RequestBody User userInfo ) {
		userDetailsServiceImpl.updateUserDetail(userInfo);
	}
	
	@GetMapping("/api/{id]/getPreviousBookings")
	public  List<Booking> getPreviousBookings(@PathVariable Long id){
		
		return userDetailsServiceImpl.getPreviousBookings(id) ;
		
	}

}
