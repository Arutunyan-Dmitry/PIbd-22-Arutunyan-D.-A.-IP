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
public class IngredientService {
    private final IDishRepository DishRepository;
    private final IIngredientRepository IngredientRepository;
    private final ValidatorUtil validatorUtil;

    public IngredientService(IDishRepository DishRepository, IIngredientRepository IngredientRepository, ValidatorUtil validatorUtil) {
        this.IngredientRepository = IngredientRepository;
        this.DishRepository = DishRepository;
        this.validatorUtil = validatorUtil;
    }

    @Transactional
    public Ingredient addIngredient(String ingredientName) {
        final Ingredient ingredient = new Ingredient(ingredientName);
        validatorUtil.validate(ingredient);
        return IngredientRepository.save(ingredient);
    }

    @Transactional
    public Ingredient addDishToIngredient(Long ingredientId, Long dishId) {
        final Optional<Dish> dish = DishRepository.findById(dishId);
        final Optional<Ingredient> ingredient = IngredientRepository.findById(ingredientId);
        ingredient.orElseThrow(() ->
                        new IngredientNotFoundException(ingredientId))
                .addDish(dish
                        .orElseThrow(() -> new DishNotFoundException(dishId)));
        return IngredientRepository.save(ingredient.orElseThrow(() -> new IngredientNotFoundException(ingredientId)));
    }

    @Transactional(readOnly = true)
    public Ingredient findIngredient(Long id) {
        final Optional<Ingredient> ingredient = IngredientRepository.findById(id);
        return ingredient.orElseThrow(() -> new IngredientNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<Ingredient> findAllIngredients() {return IngredientRepository.findAll();}

    @Transactional
    public Ingredient updateIngredient(Long id, String ingredientName) {
        final Ingredient currentIngredient = findIngredient(id);
        currentIngredient.setIngredientName(ingredientName);
        validatorUtil.validate(currentIngredient);
        return IngredientRepository.save(currentIngredient);
    }

    @Transactional
    public Ingredient deleteIngredient(Long id) {
        final Ingredient currentIngredient = findIngredient(id);
        IngredientRepository.delete(currentIngredient);
        return currentIngredient;
    }

    @Transactional
    public void deleteAllIngredients() {
        IngredientRepository.deleteAll();
    }
}
