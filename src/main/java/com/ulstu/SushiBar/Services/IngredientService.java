package com.ulstu.SushiBar.Services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.ulstu.SushiBar.Models.Dish;
import com.ulstu.SushiBar.Models.Ingredient;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class IngredientService {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Ingredient addIngredient(String ingredientName) {
        if (!StringUtils.hasText(ingredientName)) {
            throw new IllegalArgumentException("Ingredient name is null or empty");
        }
        final Ingredient ingredient = new Ingredient(ingredientName);
        em.persist(ingredient);
        return ingredient;
    }

    @Transactional
    public void addDishToIngredient(Dish dish, Ingredient ingredient) {
        if (dish.toString().isEmpty()) {
            throw new IllegalArgumentException("dish is null or empty");
        }
        ingredient.addDish(dish);
        em.merge(ingredient);
    }

    @Transactional(readOnly = true)
    public Ingredient findIngredient(Long id) {
        final Ingredient ingredient = em.find(Ingredient.class, id);
        if (ingredient == null) {
            throw new EntityNotFoundException(String.format("Ingredient with id [%s] is not found", id));
        }
        return ingredient;
    }

    @Transactional(readOnly = true)
    public List<Ingredient> findAllIngredients() {
        return em.createQuery("select i from Ingredient i", Ingredient.class)
                .getResultList();
    }

    @Transactional
    public Ingredient updateIngredient(Long id, String ingredientName) {
        if (!StringUtils.hasText(ingredientName)) {
            throw new IllegalArgumentException("Ingredient name is null or empty");
        }
        final Ingredient currentIngredient = findIngredient(id);
        currentIngredient.setIngredientName(ingredientName);
        return em.merge(currentIngredient);
    }

    @Transactional
    public Ingredient deleteIngredient(Long id) {
        final Ingredient currentIngredient = findIngredient(id);
        em.remove(currentIngredient);
        return currentIngredient;
    }

    @Transactional
    public void deleteAllIngredients() {
        em.createQuery("delete from Ingredient").executeUpdate();
    }
}
