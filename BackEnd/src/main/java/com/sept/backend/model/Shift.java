package com.sept.backend.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Represents a period of time. Employees create shifts when they enter their availability, then employers select some of these shifts to make available for bookings, after which customers make bookings for a given shift
 */
@Entity
public class Shift {

    @ManyToOne
    private User worker;
    private Date startTime;
    private Date endTime;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    public boolean isWithin(Shift shift){
        return (this.getStartTime().before(shift.getStartTime())) && (this.getEndTime().before(shift.getEndTime()));
    }

    public User getWorker() {
        return worker;
    }

    public void setWorker(User worker) {
        this.worker = worker;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getId() {
        return id;
    }
}
