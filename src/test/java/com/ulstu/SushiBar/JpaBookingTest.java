package com.ulstu.SushiBar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.ulstu.SushiBar.SushiBar.Services.BookingService;
import com.ulstu.SushiBar.SushiBar.Services.DishService;

@SpringBootTest
public class JpaBookingTest {
    private static final Logger log = LoggerFactory.getLogger(JpaIngredientTest.class);

    @Autowired
    private DishService dishService;

    @Autowired
    private BookingService bookingService;

   /* @Test
    void testOrderCreate() {
        bookingService.deleteAllBookings();
        final Booking booking = bookingService.addBooking("Принят");
        log.info(booking.toString());
        Assertions.assertNotNull(booking.getId());
    }

    @Test
    void testOrderRead() {
        bookingService.deleteAllBookings();
        final Booking booking = bookingService.addBooking("Принят");
        log.info(booking.toString());
        final Booking findBooking = bookingService.findBooking(booking.getId());
        log.info(findBooking.toString());
        Assertions.assertEquals(booking, findBooking);
    }

    @Test
    void testOrderReadNotFound() {
        bookingService.deleteAllBookings();
        Assertions.assertThrows(EntityNotFoundException.class, () -> bookingService.findBooking(-1L));
    }

    @Test
    void testOrderReadAll() {
        bookingService.deleteAllBookings();
        bookingService.addBooking("Принят");
        bookingService.addBooking("Принят");
        final List<Booking> bookings = bookingService.findAllBookings();
        log.info(bookings.toString());
        Assertions.assertEquals(bookings.size(), 2);
    }

    @Test
    void testOrderReadAllEmpty() {
        bookingService.deleteAllBookings();
        final List<Booking> bookings = bookingService.findAllBookings();
        log.info(bookings.toString());
        Assertions.assertEquals(bookings.size(), 0);
    }

    @Test
    void testAddDishToOrder() {
        dishService.deleteAllDishes();
        bookingService.deleteAllBookings();
        final Dish dish = dishService.addDish("Суша", 100);
        final Booking booking = bookingService.addBooking("Принят");
        bookingService.addDishToBooking(dish, booking);
        log.info(booking.toString());
        Assertions.assertEquals(dish, booking.getDish());
    }*/
}
