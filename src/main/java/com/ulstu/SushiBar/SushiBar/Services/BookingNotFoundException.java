package com.ulstu.SushiBar.SushiBar.Services;

public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException(Long id) {
        super(String.format("Booking with id [%s] is not found", id));
    }
}
