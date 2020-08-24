package com.sept.userlogin.dao;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sept.userlogin.models.Booking;


public interface BookingDao extends CrudRepository<Booking, BigInteger> {

}
