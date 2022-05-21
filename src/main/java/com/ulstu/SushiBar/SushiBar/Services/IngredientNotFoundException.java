package com.ulstu.SushiBar.SushiBar.Services;

public class IngredientNotFoundException extends RuntimeException {
    public IngredientNotFoundException(Long id) {
        super(String.format("Ingredient with id [%s] is not found", id));
    }
}
