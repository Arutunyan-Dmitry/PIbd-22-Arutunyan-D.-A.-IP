package com.ulstu.SushiBar.SushiBar.Controllers;

import com.ulstu.SushiBar.SushiBar.Services.BookingService;
import com.ulstu.SushiBar.SushiBar.Services.DishService;
import com.ulstu.SushiBar.user.models.UserRole;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
@RequestMapping("/booking")
public class BookingMvcController {
    private final BookingService bookingService;
    private final DishService dishService;

    public BookingMvcController(BookingService bookingService, DishService dishService)
    {
        this.bookingService = bookingService;
        this.dishService = dishService;
    }

    @GetMapping()
    @Secured({UserRole.AsString.CHEF, UserRole.AsString.CLIENT, UserRole.AsString.ADMIN})
    public String getBookings(Model model) {
        model.addAttribute("bookings",
                bookingService.findAllBookings().stream()
                        .map(BookingDto::new)
                        .toList());
        return "booking";
    }

    @GetMapping(value = {"/edit", "/edit/{id}"})
    @Secured({UserRole.AsString.CHEF, UserRole.AsString.CLIENT, UserRole.AsString.ADMIN})
    public String editBooking(@PathVariable(required = false) Long id, Model model) {
        model.addAttribute("dishes",
                dishService.findAllDishes().stream()
                        .map(DishDto::new)
                        .toList());
        if (id == null || id <= 0) {
            model.addAttribute("bookingDto", new BookingDto());
        } else {
            model.addAttribute("bookingId", id);
            model.addAttribute("bookingDto", new BookingDto(bookingService.findBooking(id)));
        }
        return "booking-edit";
    }

    @PostMapping(value = {"", "/{id}"})
    public String saveBooking(@PathVariable(required = false) Long id,
                           @ModelAttribute @Valid BookingDto bookingDto,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "booking-edit";
        }
        if (id == null || id <= 0) {
            if (bookingDto.getDish() == null || bookingDto.getAmount() == 0) {
                model.addAttribute("errors", "Dish and its amount can not be null or empty");
                return "booking-edit";
            } else {
                bookingService.addBooking(bookingDto.getStatusR(), bookingDto.getDish().getId(), bookingDto.getAmount());
            }
        } else {
            bookingService.updateBooking(id, bookingDto.getStatusR());
        }
        return "redirect:/booking";
    }

    @PostMapping("/delete/{id}")
    public String deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return "redirect:/booking";
    }
}