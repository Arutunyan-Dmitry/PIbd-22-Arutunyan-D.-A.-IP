package com.ulstu.SushiBar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.ulstu.SushiBar.Models.Dish;
import com.ulstu.SushiBar.Models.Ingredient;
import com.ulstu.SushiBar.Services.IngredientService;
import com.ulstu.SushiBar.Services.DishService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@SpringBootTest
public class JpaDishTest {
    private static final Logger log = LoggerFactory.getLogger(JpaDishTest.class);

    @Autowired
    private DishService dishService;
    @Autowired
    private IngredientService ingredientService;

    @Test
    void testDishCreate() {
        dishService.deleteAllDishes();
        final Dish dish = dishService.addDish("Суша", 100);
        log.info(dish.toString());
        Assertions.assertNotNull(dish.getId());
    }

    @Test
    void testDishRead() {
        dishService.deleteAllDishes();
        final Dish dish = dishService.addDish("Суша", 100);
        log.info(dish.toString());
        final Dish findDish = dishService.findDish(dish.getId());
        log.info(findDish.toString());
        Assertions.assertEquals(dish, findDish);
    }

    @Test
    void testDishReadNotFound() {
        dishService.deleteAllDishes();
        Assertions.assertThrows(EntityNotFoundException.class, () -> dishService.findDish(-1L));
    }

    @Test
    void testDishReadAll() {
        dishService.deleteAllDishes();
        dishService.addDish("Ролл", 200);
        dishService.addDish("Суша", 100);
        final List<Dish> dishes = dishService.findAllDishes();
        log.info(dishes.toString());
        Assertions.assertEquals(dishes.size(), 2);
    }

    @Test
    void testDishReadAllEmpty() {
        dishService.deleteAllDishes();
        final List<Dish> dishes = dishService.findAllDishes();
        log.info(dishes.toString());
        Assertions.assertEquals(dishes.size(), 0);
    }

    @Test
    void testAddExistedIngredientToExistedDish() {
        dishService.deleteAllDishes();
        ingredientService.deleteAllIngredients();
        final Ingredient ingredient = ingredientService.addIngredient("Лосось");
        final Dish dish = dishService.addDish("Суша", 100);
        final Ingredient newIngredient = ingredientService.findIngredient(ingredient.getId());
        final Dish newDish = dishService.findDish(dish.getId());
        dishService.addIngredientToDish(newIngredient, newDish);
        log.info(newIngredient.toString());
        Assertions.assertEquals(newIngredient, newDish.getIngredients().get(0));
    }
}
