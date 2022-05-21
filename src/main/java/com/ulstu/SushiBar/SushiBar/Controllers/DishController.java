package com.ulstu.SushiBar.SushiBar.Controllers;

import com.ulstu.SushiBar.configuration.WebConfiguration;
import org.springframework.web.bind.annotation.*;
import com.ulstu.SushiBar.SushiBar.Models.Dish;
import com.ulstu.SushiBar.SushiBar.Services.DishService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(WebConfiguration.REST_API + "/dish")
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
    public List<DishDto> getDishes() {
        return dishService.findAllDishes().stream()
            .map(DishDto::new)
            .toList();
    }

    @PostMapping("/")
    public DishDto createDish(@RequestBody @Valid DishDto dishDto)
    {
        return new DishDto(dishService.addDish(dishDto.getName(), dishDto.getPrice()));
    }

    @PutMapping("/")
    public DishDto addIngredientToDIsh(@RequestBody @Valid DishIngredientDto dishIngredientDto)
    {
        return new DishDto(dishService.addIngredientToDish(dishIngredientDto.getDishId(),
                dishIngredientDto.getIngredientId()));
    }

    @PatchMapping("/{id}")
    public DishDto updateDish(@PathVariable Long id,
                           @RequestBody @Valid DishDto dishDto)
    {
        return new DishDto(dishService.updateDish(id, dishDto.getName(), dishDto.getPrice()));
    }

    @DeleteMapping("/{id}")
    public DishDto deleteDish(@PathVariable Long id) {
        return new DishDto(dishService.deleteDish(id));
    }

    @DeleteMapping("")
    public void deleteDishes() {dishService.deleteAllDishes();}
}