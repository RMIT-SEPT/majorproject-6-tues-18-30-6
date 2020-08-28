package com.sept.userlogin.security.services;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sept.userlogin.dao.BookingDao;
import com.sept.userlogin.exceptions.BookingAlreadyPresentException;
import com.sept.userlogin.exceptions.BookingNotFoundException;
import com.sept.userlogin.models.Booking;
public class BookingServiceImpl implements BookingService {
	/*
	 * Creating DAO object
	 */
	@Autowired
	BookingDao bookingDao;

	/*
	 * making new Booking
	 */
	public ResponseEntity<Booking> createBooking(Booking newBooking) {

		Optional<Booking> findBookingById = bookingDao.findById(newBooking.getBookingId());
		try {
			if (!findBookingById.isPresent()) {
				bookingDao.save(newBooking);
				return new ResponseEntity<Booking>(newBooking, HttpStatus.OK);
			} else
				throw new BookingAlreadyPresentException(
						"Booking with Booking Id: " + newBooking.getBookingId() + " already exists!!");
		} catch (BookingAlreadyPresentException e) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/*
	 * update booking made
	 */
	public Booking updateBooking(Booking changedBooking) {
		Optional<Booking> findBookingById = bookingDao.findById(changedBooking.getBookingId());
		if (findBookingById.isPresent()) {
			bookingDao.save(changedBooking);
		} else
			throw new BookingNotFoundException(
					"Booking with Booking Id: " + changedBooking.getBookingId() + " not exists!!");
		return changedBooking;
	}

	/*
	 * deleteing the booking
	 */

	public String deleteBooking(BigInteger bookingId) {

		Optional<Booking> findBookingById = bookingDao.findById(bookingId);
		if (findBookingById.isPresent()) {
			bookingDao.deleteById(bookingId);
			return "Booking Deleted!!";
		} else
			throw new BookingNotFoundException("Booking not found for the entered BookingID");
	}

	public Iterable<Booking> displayAllBooking() {

		return bookingDao.findAll();
	}

	/*
	 * find booking by ID
	 */
	public ResponseEntity<?> findBookingById(BigInteger bookingId) {
		Optional<Booking> findById = bookingDao.findById(bookingId);
		try {
			if (findById.isPresent()) {
				Booking findBooking = findById.get();
				return new ResponseEntity<Booking>(findBooking, HttpStatus.OK);
			} else
				throw new BookingNotFoundException("No record found with ID " + bookingId);
		} catch (BookingNotFoundException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
