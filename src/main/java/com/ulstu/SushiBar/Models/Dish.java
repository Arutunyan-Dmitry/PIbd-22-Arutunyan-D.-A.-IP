package com.ulstu.SushiBar.Models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column()
    private String dishName;
    private int price;
    @ManyToMany(fetch=FetchType.EAGER, mappedBy = "dishes")
    private List<Ingredient> ingredients;

    public Dish() {}

    public Dish(String dishName, int price) {
        this.dishName = dishName;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getDishName() {
        return dishName;
    }
    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public List<Ingredient> getIngredients(){return this.ingredients;}
    public void setIngredients (List<Ingredient> ingredients)
    {
        this.ingredients = ingredients;
    }
    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        if (!ingredient.getDishes().contains(this)) {
            ingredient.addDish(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return Objects.equals(id, dish.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", dishName='" + dishName + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}