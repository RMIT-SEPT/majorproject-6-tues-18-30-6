package com.sept.dao;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sept.models.Booking;


public interface BookingDao extends CrudRepository<Booking, BigInteger> {

}
