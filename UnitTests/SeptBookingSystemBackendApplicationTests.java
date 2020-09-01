package com.example.backend;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.backend.model.Business;
import com.example.backend.model.BusinessType;
import com.example.backend.model.EBusinessType;

@SpringBootTest
class SeptBookingSystemBackendApplicationTests {

    @Test
    void contextLoads() {
    }
    @Test
    void businessTypeIdTest() {
    	BusinessType mock=new BusinessType();
    	mock.setId(1);
    	assertTrue(mock.getId()==1);
    	mock.setId(2);
    	assertTrue(mock.getId()==2);
    }
    
    @Test
    void businessTypeNameTest(){
    	EBusinessType EB =EBusinessType.TYPE_FOOD;
    	BusinessType mock=new BusinessType(EB);
    	assertTrue(mock.getName()==EB);
    }
    
    @Test
    void businessTest() {
    	long postIdFromFrontEnd=1;
    	Business mock = new Business();
    	mock.setId(postIdFromFrontEnd);
    	assertTrue(mock.getId()==postIdFromFrontEnd);
    }
    @Test
    public void testSaveBooking(){
        Booking mock = new Booking();
        BookingService bs=new BookingServiceImpl();
        mock.setBookingId(BigInteger.valueOf(1));
        mock.setBookingDate("1");
        mock.setNoOfPassengers(1);
        bs.createBooking(mock);
    }
    @Test(expected = com.sept.userlogin.exceptions.BookingNotFoundException)
    public void updateBookingShouldThrow() {
        Booking mock = new Booking();
        BookingService bs=new BookingServiceImpl();
        mock.setBookingId(BigInteger.valueOf(1));
        mock.setBookingDate("1");
        mock.setNoOfPassengers(1);
        bs.updateBooking(mock);
    }
    @Test
    public void shouldUpdateSuccessfully(){
        Booking mock = new Booking();
        BookingService bs=new BookingServiceImpl();
        mock.setBookingId(BigInteger.valueOf(1));
        mock.setBookingDate("1");
        mock.setNoOfPassengers(1);
        bs.createBooking(mock);
        Booking changedBooking = new Booking();
        changedBooking.setBookingId(BigInteger.valueOf(1));
        changedBooking.setBookingDate("1");
        changedBooking.setNoOfPassengers(2);
        Booking result=bs.updateBooking(changedBooking);
        assertTrue(result.getNoOfPassengers()==2);
    }
    @Test(expected = com.sept.userlogin.exceptions.BookingNotFoundException)
    public void deleteBookingShouldThrow() {
        Booking mock = new Booking();
        BookingService bs=new BookingServiceImpl();
        mock.setBookingId(BigInteger.valueOf(1));
        mock.setBookingDate("1");
        mock.setNoOfPassengers(1);
        bs.deleteBooking(mock);
    }

}
