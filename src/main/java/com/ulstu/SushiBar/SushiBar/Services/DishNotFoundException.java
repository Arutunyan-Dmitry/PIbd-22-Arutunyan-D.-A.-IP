package com.ulstu.SushiBar.SushiBar.Services;

public class DishNotFoundException extends RuntimeException {
    public DishNotFoundException(Long id) {
        super(String.format("Dish with id [%s] is not found", id));
    }
}
