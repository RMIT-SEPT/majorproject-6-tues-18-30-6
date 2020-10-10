package com.sept.backend.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private Date startTime;
    private Date endTime;
    private User customer;
    private User worker;
    private Business business;
}
