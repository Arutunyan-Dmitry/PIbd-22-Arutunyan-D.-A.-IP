package com.ulstu.SushiBar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JpaIngredientTest {
    private static final Logger log = LoggerFactory.getLogger(JpaIngredientTest.class);

   /* @Autowired
    private IngredientService ingredientService;
    @Autowired
    private DishService dishService;

    @Test
    void testIngredientCreate() {
        ingredientService.deleteAllIngredients();
        final Ingredient ingredient = ingredientService.addIngredient("Лосось");
        log.info(ingredient.toString());
        Assertions.assertNotNull(ingredient.getId());
    }

    @Test
    void testIngredientRead() {
        ingredientService.deleteAllIngredients();
        final Ingredient ingredient = ingredientService.addIngredient("Лосось");
        log.info(ingredient.toString());
        final Ingredient findIngredient = ingredientService.findIngredient(ingredient.getId());
        log.info(findIngredient.toString());
        Assertions.assertEquals(ingredient, findIngredient);
    }

    @Test
    void testIngredientReadNotFound() {
        ingredientService.deleteAllIngredients();
        Assertions.assertThrows(EntityNotFoundException.class, () -> ingredientService.findIngredient(-1L));
    }

    @Test
    void testIngredientReadAll() {
        ingredientService.deleteAllIngredients();
        ingredientService.addIngredient("Лосось");
        ingredientService.addIngredient("Рис");
        final List<Ingredient> ingredients = ingredientService.findAllIngredients();
        log.info(ingredients.toString());
        Assertions.assertEquals(ingredients.size(), 2);
    }

    @Test
    void testIngredientReadAllEmpty() {
        ingredientService.deleteAllIngredients();
        final List<Ingredient> ingredients = ingredientService.findAllIngredients();
        log.info(ingredients.toString());
        Assertions.assertEquals(ingredients.size(), 0);
    }

    @Test
    void testAddExistedDishToExistedIngredient() {
        dishService.deleteAllDishes();
        ingredientService.deleteAllIngredients();
        final Ingredient ingredient = ingredientService.addIngredient("Лосось");
        final Dish dish = dishService.addDish("Суша", 100);
        final Ingredient newIngredient = ingredientService.findIngredient(ingredient.getId());
        final Dish newDish = dishService.findDish(dish.getId());
        ingredientService.addDishToIngredient(newDish, newIngredient);
        log.info(newIngredient.toString());
        Assertions.assertEquals(newDish, newIngredient.getDishes().get(0));
    }*/
}
