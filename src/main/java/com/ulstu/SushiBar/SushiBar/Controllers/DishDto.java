package com.ulstu.SushiBar.SushiBar.Controllers;

import com.ulstu.SushiBar.SushiBar.Models.Dish;
import com.ulstu.SushiBar.SushiBar.Models.Ingredient;
import java.util.List;

public class DishDto {
    private final long id;
    private final String Name;
    private final int Price;
    private final List<Ingredient> ingredients;

    public DishDto(Dish dish) {
        this.id = dish.getId();
        this.Name = String.format("%s", dish.getDishName());
        this.Price = dish.getPrice();
        this.ingredients = dish.getIngredients();
    }

    public long getId() {
        return id;
    }

    public String getName() { return Name;}

    public int getPrice() { return Price; }

    public List<Ingredient> getIngredients() { return ingredients; }
}
