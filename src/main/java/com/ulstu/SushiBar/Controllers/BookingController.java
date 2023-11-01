package com.ulstu.SushiBar.Controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ulstu.SushiBar.Models.Booking;
import com.ulstu.SushiBar.Services.BookingService;
import java.util.List;

@RestController
@RequestMapping("/order")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService orderService) {
        this.bookingService = orderService;
    }

    @GetMapping("/{id}")
    public Booking getBooking(@PathVariable Long id) {
        return bookingService.findBooking(id);
    }

    @GetMapping("/")
    public List<Booking> getBookings() {
        return List.of(new Booking("1"), new Booking("2"), new Booking("3"));
    }

    @PostMapping("/")
    public Booking createBooking(@RequestParam("status") String status) {
        return bookingService.addBooking(status);
    }

    @PatchMapping("/{id}")
    public Booking updateBooking(@PathVariable Long id,
                               @RequestParam("status") String status) {
        return bookingService.updateBooking(id, status);
    }

    @DeleteMapping("/{id}")
    public Booking deleteBooking(@PathVariable Long id) {
        return bookingService.deleteBooking(id);
    }
}
