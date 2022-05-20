package com.ulstu.SushiBar.SushiBar.Controllers;

import org.springframework.web.bind.annotation.*;
import com.ulstu.SushiBar.SushiBar.Models.Dish;
import com.ulstu.SushiBar.SushiBar.Services.DishService;
import java.util.List;

@RestController
@RequestMapping("/dish")
public class DishController {
    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping("/{id}")
    public DishDto getDish(@PathVariable Long id) {
        return new DishDto(dishService.findDish(id));
    }

    @GetMapping("/")
    public List<DishDto> getDishes() { return dishService.findAllDishes().stream()
            .map(DishDto::new)
            .toList();
    }

    @PostMapping("/")
    public DishDto createDish(@RequestParam("dishName") String dishName,
                           @RequestParam("price") int price)
    {
        return new DishDto(dishService.addDish(dishName, price));
    }

    @PutMapping("/{dishId} {ingredientId}")
    public DishDto addIngredientToDIsh(@RequestParam("dishId") Long dishId,
                                       @RequestParam("ingredientId") Long ingredientId)
    {
        return new DishDto(dishService.addIngredientToDish(dishId, ingredientId));
    }

    @PatchMapping("/{id}")
    public DishDto updateDish(@PathVariable Long id,
                           @RequestParam("dishName") String dishName,
                           @RequestParam("price") int price)
    {
        return new DishDto(dishService.updateDish(id, dishName, price));
    }

    @DeleteMapping("/{id}")
    public DishDto deleteDish(@PathVariable Long id) {
        return new DishDto(dishService.deleteDish(id));
    }

    @DeleteMapping("")
    public void deleteDishes() {dishService.deleteAllDishes();}
}