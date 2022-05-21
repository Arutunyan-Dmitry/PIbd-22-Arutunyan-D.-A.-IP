package com.ulstu.SushiBar.SushiBar.Controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ulstu.SushiBar.SushiBar.Models.Dish;
import com.ulstu.SushiBar.SushiBar.Models.Ingredient;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

public class DishDto {
    private long id;
    @NotBlank(message = "Name can't be null or empty")
    private String Name;
    @NotNull(message = "Price can't be null or empty")
    @Min(value = 1, message = "Price should be greater than zero")
    private int Price;
    private List<Ingredient> ingredients;

    public DishDto() { }

    public DishDto(Dish dish) {
        this.id = dish.getId();
        this.Name = dish.getDishName();
        this.Price = dish.getPrice();
        this.ingredients = dish.getIngredients();
    }
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public long getId() {
        return id;
    }

    public String getName() { return Name;}
    public void setName(String name) {
        Name = name;
    }

    public int getPrice() { return Price; }
    public void setPrice(int price) {
        Price = price;
    }

    public List<Ingredient> getIngredients() { return ingredients; }
    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
