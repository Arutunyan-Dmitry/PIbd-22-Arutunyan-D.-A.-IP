package com.ulstu.SushiBar.SushiBar.Controllers;

import com.ulstu.SushiBar.SushiBar.Services.DishService;
import com.ulstu.SushiBar.WebConfiguration;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.ulstu.SushiBar.SushiBar.Models.Booking;
import com.ulstu.SushiBar.SushiBar.Services.BookingService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(WebConfiguration.REST_API + "/booking")
public class BookingController {
    private final BookingService bookingService;
    private final DishService dishService;

    public BookingController(BookingService bookingService, DishService dishService)
    {
        this.dishService = dishService;
        this.bookingService = bookingService;
    }

    @GetMapping("/{id}")
    public BookingDto getBooking(@PathVariable Long id) {
        return new BookingDto(bookingService.findBooking(id));
    }

    @GetMapping("/")
    public List<BookingDto> getBookings()
    {
        return bookingService.findAllBookings().stream()
                .map(BookingDto::new)
                .toList();
    }

    @PostMapping("/")
    public BookingDto createBooking(@RequestBody @Valid BookingDto bookingDto)
    {
        return new BookingDto(bookingService.addBooking(bookingDto.getStatusR(),
                bookingDto.getDish().getId(), bookingDto.getAmount()));
    }

    @PatchMapping("/{id}")
    public BookingDto updateBooking(@PathVariable Long id,
                                    @RequestBody @Valid BookingDto bookingDto)
    {
        return new BookingDto(bookingService.updateBooking(id, bookingDto.getStatusR()));
    }

    @DeleteMapping("/{id}")
    public BookingDto deleteBooking(@PathVariable Long id) {
        return new BookingDto(bookingService.deleteBooking(id));
    }

    @DeleteMapping("")
    public void deleteBookings() {bookingService.deleteAllBookings();}
}
