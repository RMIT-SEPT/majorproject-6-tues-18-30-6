package com.sept.backend.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Business {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String Abn;
    @Enumerated(EnumType.STRING)
    private Category category;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Shift> employeeAvailability;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Shift> availableBookings;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Booking> bookings;

    public boolean Book(Booking booking){
        Shift bookingShift = null;
        for(Shift s : availableBookings){
            if (booking.getTime().isWithin(s) && booking.getTime().getWorker().equals(s.getWorker())){
                bookingShift = s;
                break;
            }
        }

        if(bookingShift == null){
            return false;
        }

        //Checks whether the booking is at the start or end of the available shift (in which case the shift can simply be shortened)
        if(bookingShift.getStartTime().compareTo(booking.getTime().getStartTime()) == 0){
            bookingShift.setStartTime(booking.getTime().getEndTime());
        } else if(bookingShift.getEndTime().compareTo(booking.getTime().getEndTime()) == 0){
            bookingShift.setEndTime(booking.getTime().getStartTime());
        } else{
            //Otherwise we split the booking
            Shift newShift = new Shift();
            newShift.setEndTime(bookingShift.getEndTime());
            newShift.setWorker(bookingShift.getWorker());
            newShift.setStartTime(booking.getTime().getEndTime());
            bookingShift.setEndTime(booking.getTime().getStartTime());
            availableBookings.add(newShift);
        }
        return true;
    }

    public List<Shift> getEmployeeAvailability() {
        return employeeAvailability;
    }

    public void setEmployeeAvailability(List<Shift> employeeAvailability) {
        this.employeeAvailability = employeeAvailability;
    }

    public void addEmployeeAvailability(List<Shift> employeeAvailability) {
        this.employeeAvailability.addAll(employeeAvailability);
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Shift> getAvailableBookings() {
        return availableBookings;
    }

    public void setAvailableBookings(List<Shift> availableBookings) {
        this.availableBookings = availableBookings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbn() {
        return Abn;
    }

    public void setAbn(String Abn) {
        this.Abn = Abn;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }
}
