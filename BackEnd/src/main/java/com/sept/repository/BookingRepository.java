package com.sept.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sept.models.Booking;
import com.sept.models.Business;

//you need to have use id and business id in a booking, as foreign key
//otherwise I can not join table query 

@Repository
public interface BookingRepository extends JpaRepository<Business, Long>{
	
	@Query("select b from Booking b where b.userId=?1")
	List<Booking> getPreviousBookings(Long id);
	

}
