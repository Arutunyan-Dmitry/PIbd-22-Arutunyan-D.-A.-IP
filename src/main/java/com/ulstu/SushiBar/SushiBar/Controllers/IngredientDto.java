package com.ulstu.SushiBar.SushiBar.Controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ulstu.SushiBar.SushiBar.Models.Dish;
import com.ulstu.SushiBar.SushiBar.Models.Ingredient;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

public class IngredientDto {
    private long id;
    @NotBlank(message = "Name can't be null or empty")
    private String Name;
    @NotNull(message = "Grm can't be null or empty")
    @Min(value = 1, message = "Weight of ingredient should be greater than zero gr")
    private int Grm;
    private List<Dish> dishes;

    public IngredientDto(){ }

    public IngredientDto(Ingredient ingredient) {
        this.id = ingredient.getId();
        this.Name = ingredient.getIngredientName();
        this.Grm = ingredient.getGrm();
        this.dishes = ingredient.getDishes();
    }
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public long getId() {
        return id;
    }

    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }

    public int getGrm() {return Grm;}
    public void setGrm(int Grm) {this.Grm = Grm;}

    public List<Dish> getDishes() {return dishes;}
    public void setDishes(List<Dish> dishes) {this.dishes = dishes;}
}
