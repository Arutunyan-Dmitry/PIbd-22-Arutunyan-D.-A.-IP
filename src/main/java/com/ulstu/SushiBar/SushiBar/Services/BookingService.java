package com.ulstu.SushiBar.SushiBar.Services;

import com.ulstu.SushiBar.SushiBar.Repository.IBookingRepository;
import com.ulstu.SushiBar.SushiBar.Repository.IDishRepository;
import com.ulstu.SushiBar.util.validation.ValidatorUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ulstu.SushiBar.SushiBar.Models.Booking;
import com.ulstu.SushiBar.SushiBar.Models.Dish;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private final IDishRepository DishRepository;
    private final IBookingRepository BookingRepository;
    private final ValidatorUtil validatorUtil;

    public BookingService(IBookingRepository BookingRepository, IDishRepository DishRepository, ValidatorUtil validatorUtil) {
        this.BookingRepository = BookingRepository;
        this.validatorUtil = validatorUtil;
        this.DishRepository = DishRepository;
    }

    @Transactional
    public Booking addBooking(String status, Long dishId, int amount) {
        final Optional<Dish> dish = DishRepository.findById(dishId);
        int pavement = amount * dish.orElseThrow(() -> new DishNotFoundException(dishId)).getPrice();
        final Booking booking = new Booking(status,
                dish.orElseThrow(() -> new DishNotFoundException(dishId)),
                amount, pavement);
        validatorUtil.validate(booking);
        return BookingRepository.save(booking);
    }

    @Transactional(readOnly = true)
    public Booking findBooking(Long id) {
        final Optional<Booking> booking = BookingRepository.findById(id);
        return booking.orElseThrow(() -> new BookingNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<Booking> findAllBookings() {return BookingRepository.findAll();}

    @Transactional
    public List<Booking> findFilteredBookings(String searchStatus) {
       return BookingRepository.findAll()
               .stream().filter(p -> p.getStatusR().equals(searchStatus)).toList();
    }

    @Transactional
    public Booking updateBooking(Long id, String status) {
        final Booking currentBooking = findBooking(id);
        currentBooking.setStatusR(status);
        validatorUtil.validate(currentBooking);
        return BookingRepository.save(currentBooking);
    }

    @Transactional
    public Booking deleteBooking(Long id) {
        final Booking currentBooking = findBooking(id);
        BookingRepository.delete(currentBooking);
        return currentBooking;
    }

    @Transactional
    public void deleteAllBookings() {
        BookingRepository.deleteAll();
    }
}
