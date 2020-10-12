package com.sept.backend.model;

import javax.persistence.*;


@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private Shift time;
    @OneToOne
    private User customer;
    @OneToOne
    private User worker;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Shift getTime() {
        return time;
    }

    public void setTime(Shift time) {
        this.time = time;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public User getWorker() {
        return worker;
    }

    public void setWorker(User worker) {
        this.worker = worker;
    }
}
