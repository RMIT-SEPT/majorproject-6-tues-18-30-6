package com.sept.backend.payload;

import com.sept.backend.model.Shift;

import java.util.List;

public class AvailabilityRequest {
    Long businessId;
    List<Shift> availability;

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public List<Shift> getAvailability() {
        return availability;
    }

    public void setAvailability(List<Shift> availability) {
        this.availability = availability;
    }
}
