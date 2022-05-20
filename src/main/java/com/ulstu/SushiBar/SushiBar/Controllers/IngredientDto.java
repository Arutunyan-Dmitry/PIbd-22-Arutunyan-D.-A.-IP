package com.ulstu.SushiBar.SushiBar.Controllers;

import com.ulstu.SushiBar.SushiBar.Models.Ingredient;

public class IngredientDto {
    private final long id;
    private final String Name;

    public IngredientDto(Ingredient ingredient) {
        this.id = ingredient.getId();
        this.Name = String.format("%s", ingredient.getIngredientName());
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return Name;
    }
}
