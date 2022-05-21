package com.ulstu.SushiBar.SushiBar.Repository;

import com.ulstu.SushiBar.SushiBar.Models.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDishRepository extends JpaRepository<Dish, Long> {
}
