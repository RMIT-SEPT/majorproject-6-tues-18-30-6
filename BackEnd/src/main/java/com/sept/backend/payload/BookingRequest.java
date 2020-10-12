package com.sept.backend.payload;

import com.sept.backend.model.Booking;

import javax.validation.constraints.NotBlank;

public class BookingRequest {


    private Long businessId;

    private Booking booking;

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
