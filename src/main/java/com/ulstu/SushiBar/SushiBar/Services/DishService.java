package com.ulstu.SushiBar.SushiBar.Services;

import com.ulstu.SushiBar.SushiBar.Repository.IDishRepository;
import com.ulstu.SushiBar.SushiBar.Repository.IIngredientRepository;
import com.ulstu.SushiBar.util.validation.ValidatorUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.ulstu.SushiBar.SushiBar.Models.Dish;
import com.ulstu.SushiBar.SushiBar.Models.Ingredient;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class DishService {
    private final IIngredientRepository IngredientRepository;
    private final IDishRepository DishRepository;
    private final ValidatorUtil validatorUtil;

    public DishService(IIngredientRepository IngredientRepository, IDishRepository DishRepository, ValidatorUtil validatorUtil) {
        this.IngredientRepository = IngredientRepository;
        this.DishRepository = DishRepository;
        this.validatorUtil = validatorUtil;
    }

    @Transactional
    public Dish addDish(String dishName, int price) {
        final Dish dish = new Dish(dishName, price);
        validatorUtil.validate(dish);
        return DishRepository.save(dish);
    }

    @Transactional
    public Dish addIngredientToDish(Long dishId, Long ingredientId) {
        final Optional<Dish> dish = DishRepository.findById(dishId);
        final Optional<Ingredient> ingredient = IngredientRepository.findById(ingredientId);
        dish.orElseThrow(() ->
                new DishNotFoundException(dishId))
                .addIngredient(ingredient
                        .orElseThrow(() -> new IngredientNotFoundException(ingredientId)));
        return DishRepository.save(dish.orElseThrow(() -> new DishNotFoundException(dishId)));
    }

    @Transactional(readOnly = true)
    public Dish findDish(Long id) {
        final Optional<Dish> dish = DishRepository.findById(id);
        return dish.orElseThrow(() -> new DishNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<Dish> findAllDishes() {return DishRepository.findAll();}

    @Transactional
    public Dish updateDish(Long id, String dishName, int price) {
        final Dish currentDish = findDish(id);
        currentDish.setDishName(dishName);
        currentDish.setPrice(price);
        validatorUtil.validate(currentDish);
        return DishRepository.save(currentDish);
    }

    @Transactional
    public Dish deleteDish(Long id) {
        final Dish currentDish = findDish(id);
        DishRepository.delete(currentDish);
        return currentDish;
    }

    @Transactional
    public void deleteAllDishes() {DishRepository.deleteAll();}
}
