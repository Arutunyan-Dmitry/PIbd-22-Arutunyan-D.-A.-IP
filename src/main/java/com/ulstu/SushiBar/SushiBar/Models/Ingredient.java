package com.ulstu.SushiBar.SushiBar.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column()
    private String ingredientName;
    private int Grm;
    @ManyToMany(fetch=FetchType.EAGER, mappedBy = "ingredients")
    @JsonBackReference
    private List<Dish> dishes;

    public Ingredient() {}

    public Ingredient(String ingredientName, int Grm) {
        this.ingredientName = ingredientName;
        this.Grm = Grm;
    }

    public Long getId() {
        return id;
    }

    public String getIngredientName() {
        return ingredientName;
    }
    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public int getGrm() {return Grm;}
    public void setGrm(int Grm) {this.Grm = Grm;}

    public List<Dish> getDishes(){return this.dishes;}
    public void setDishes(List<Dish> dishes) {this.dishes = dishes;}
    public void addDish(Dish dish) {
        dishes.add(dish);
        if (!dish.getIngredients().contains(this)) {
            dish.addIngredient(this);
        }
    }
    public void removeDish(Dish dish) {
        dishes.remove(dish);
        if (dish.getIngredients().contains(this)) {
            dish.removeIngredient(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient ingredient = (Ingredient) o;
        return Objects.equals(id, ingredient.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", ingredientName='" + ingredientName + '\'' +
                ", Grm='" + Grm + '\'' +
                '}';
    }
}