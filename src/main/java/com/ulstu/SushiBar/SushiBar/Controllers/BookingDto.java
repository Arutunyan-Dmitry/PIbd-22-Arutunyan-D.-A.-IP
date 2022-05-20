package com.ulstu.SushiBar.SushiBar.Controllers;

import com.ulstu.SushiBar.SushiBar.Models.Booking;
import com.ulstu.SushiBar.SushiBar.Models.Dish;

public class BookingDto {
    private final long id;
    private final String StatusR;
    private final Dish dish;

    public BookingDto(Booking booking) {
        this.id = booking.getId();
        this.StatusR = String.format("%s", booking.getStatusR());
        this.dish = booking.getDish();
    }

    public long getId() {
        return id;
    }

    public String getStatusR() { return StatusR;}

    public Dish getDish() { return dish; }
}
