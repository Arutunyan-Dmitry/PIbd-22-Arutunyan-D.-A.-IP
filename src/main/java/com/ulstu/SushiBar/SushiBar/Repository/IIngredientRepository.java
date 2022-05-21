package com.ulstu.SushiBar.SushiBar.Repository;

import com.ulstu.SushiBar.SushiBar.Models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IIngredientRepository extends JpaRepository<Ingredient, Long> {
}
