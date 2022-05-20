package com.ulstu.SushiBar.Services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.ulstu.SushiBar.Models.Booking;
import com.ulstu.SushiBar.Models.Dish;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class BookingService {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Booking addBooking(String status) {
        if (!StringUtils.hasText(status)) {
            throw new IllegalArgumentException("Booking status is null or empty");
        }
        final Booking booking = new Booking(status);
        em.persist(booking);
        return booking;
    }

    @Transactional
    public void addDishToBooking(Dish dish, Booking booking) {
        if (dish.toString().isEmpty()) {
            throw new IllegalArgumentException("dish is null or empty");
        }
        booking.setDish(dish);
        em.merge(booking);
    }

    @Transactional(readOnly = true)
    public Booking findBooking(Long id) {
        final Booking booking = em.find(Booking.class, id);
        if (booking == null) {
            throw new EntityNotFoundException(String.format("Booking with id [%s] is not found", id));
        }
        return booking;
    }

    @Transactional(readOnly = true)
    public List<Booking> findAllBookings() {
        return em.createQuery("select b from Booking b", Booking.class)
                .getResultList();
    }

    @Transactional
    public Booking updateBooking(Long id, String status) {
        if (!StringUtils.hasText(status)) {
            throw new IllegalArgumentException("Status name is null or empty");
        }
        final Booking currentBooking = findBooking(id);
        currentBooking.setStatus(status);
        return em.merge(currentBooking);
    }

    @Transactional
    public Booking deleteBooking(Long id) {
        final Booking currentBooking = findBooking(id);
        em.remove(currentBooking);
        return currentBooking;
    }

    @Transactional
    public void deleteAllBookings() {
        em.createQuery("delete from Booking").executeUpdate();
    }
}
