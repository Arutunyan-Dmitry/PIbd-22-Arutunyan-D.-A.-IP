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
public class DishService {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Dish addDish(String dishName, int price) {
        if (!StringUtils.hasText(dishName) || (price == 0)) {
            throw new IllegalArgumentException("Dish name is null or empty");
        }
        final Dish dish = new Dish(dishName, price);
        em.persist(dish);
        return dish;
    }

    @Transactional
    public void addIngredientToDish(Ingredient ingredient, Dish dish) {
        if (dish.toString().isEmpty()) {
            throw new IllegalArgumentException("ingredient is null or empty");
        }
        dish.addIngredient(ingredient);
        em.merge(dish);
    }

    @Transactional(readOnly = true)
    public Dish findDish(Long id) {
        final Dish dish = em.find(Dish.class, id);
        if (dish == null) {
            throw new EntityNotFoundException(String.format("Dish with id [%s] is not found", id));
        }
        return dish;
    }

    @Transactional(readOnly = true)
    public List<Dish> findAllDishes() {
        return em.createQuery("select d from Dish d", Dish.class)
                .getResultList();
    }

    @Transactional
    public Dish updateDish(Long id, String dishName, int price) {
        if (!StringUtils.hasText(dishName) || (price == 0)) {
            throw new IllegalArgumentException("Dish name, price is null or empty");
        }
        final Dish currentDish = findDish(id);
        currentDish.setDishName(dishName);
        currentDish.setPrice(price);
        return em.merge(currentDish);
    }

    @Transactional
    public Dish deleteDish(Long id) {
        final Dish currentDish = findDish(id);
        em.remove(currentDish);
        return currentDish;
    }

    @Transactional
    public void deleteAllDishes() {
        em.createQuery("delete from Dish").executeUpdate();
    }
}
