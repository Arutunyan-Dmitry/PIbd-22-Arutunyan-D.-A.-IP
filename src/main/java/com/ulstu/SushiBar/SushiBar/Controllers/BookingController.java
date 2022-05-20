package com.ulstu.SushiBar.SushiBar.Controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ulstu.SushiBar.SushiBar.Models.Booking;
import com.ulstu.SushiBar.SushiBar.Services.BookingService;
import java.util.List;

@RestController
@RequestMapping("/order")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/{id}")
    public BookingDto getBooking(@PathVariable Long id) {
        return new BookingDto(bookingService.findBooking(id));
    }

    @GetMapping("/")
    public List<BookingDto> getBookings() {
        return bookingService.findAllBookings().stream()
                .map(BookingDto::new)
                .toList();
    }

    @PostMapping("/")
    public BookingDto createBooking(@RequestParam("statusR") String statusR,
                                    @RequestParam("dishId") Long dishId) {
        return new BookingDto(bookingService.addBooking(statusR, dishId));
    }

    @PatchMapping("/{id}")
    public BookingDto updateBooking(@PathVariable Long id,
                               @RequestParam("statusR") String statusR) {
        return new BookingDto(bookingService.updateBooking(id, statusR));
    }

    @DeleteMapping("/{id}")
    public BookingDto deleteBooking(@PathVariable Long id) {
        return new BookingDto(bookingService.deleteBooking(id));
    }

    @DeleteMapping("")
    public void deleteBookings() {bookingService.deleteAllBookings();}
}
