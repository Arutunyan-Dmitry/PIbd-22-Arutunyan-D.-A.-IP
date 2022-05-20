package com.ulstu.SushiBar.Controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ulstu.SushiBar.Models.Dish;
import com.ulstu.SushiBar.Services.DishService;
import java.util.List;

@RestController
@RequestMapping("/dish")
public class DishController {
    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping("/{id}")
    public Dish getDish(@PathVariable Long id) {
        return dishService.findDish(id);
    }

    @GetMapping("/")
    public List<Dish> getDishes() {
        return dishService.findAllDishes();
    }

    @PostMapping("/")
    public Dish createDish(@RequestParam("dishName") String dishName,
                           @RequestParam("price") int price)
    {
        return dishService.addDish(dishName, price);
    }

    @PatchMapping("/{id}")
    public Dish updateDish(@PathVariable Long id,
                           @RequestParam("dishName") String dishName,
                           @RequestParam("price") int price)
    {
        return dishService.updateDish(id, dishName, price);
    }

    @DeleteMapping("/{id}")
    public Dish deleteDish(@PathVariable Long id) {
        return dishService.deleteDish(id);
    }
}