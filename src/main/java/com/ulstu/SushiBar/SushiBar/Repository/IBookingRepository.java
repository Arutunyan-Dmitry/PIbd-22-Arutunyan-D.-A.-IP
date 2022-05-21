package com.ulstu.SushiBar.SushiBar.Repository;

import com.ulstu.SushiBar.SushiBar.Models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookingRepository extends JpaRepository<Booking, Long> {
}
