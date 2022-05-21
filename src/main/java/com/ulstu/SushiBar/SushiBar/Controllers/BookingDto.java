package com.ulstu.SushiBar.SushiBar.Controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ulstu.SushiBar.SushiBar.Models.Booking;
import com.ulstu.SushiBar.SushiBar.Models.Dish;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BookingDto {
    private long id;
    @NotBlank(message = "Status can't be null or empty")
    private String StatusR;
    private Dish dish;
    private int amount;
    private int pavement;

    public BookingDto() { }

    public BookingDto(Booking booking) {
        this.id = booking.getId();
        this.StatusR = String.format("%s", booking.getStatusR());
        this.dish = booking.getDish();
        this.amount = booking.getAmount();
        this.pavement = booking.getPavement();
    }
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public long getId() {
        return id;
    }

    public String getStatusR() { return StatusR;}
    public void setStatusR(String statusR) {
        StatusR = statusR;
    }

    public Dish getDish() { return dish; }
    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public int getAmount() {return amount;}
    public void setAmount(int amount) {this.amount = amount;}

    public int getPavement() {return pavement;}
    public void setPavement(int pavement) {this.pavement = pavement;}
}
