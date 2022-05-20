package com.ulstu.SushiBar.SushiBar.Controllers;

import org.springframework.web.bind.annotation.*;
import com.ulstu.SushiBar.SushiBar.Models.Ingredient;
import com.ulstu.SushiBar.SushiBar.Services.IngredientService;
import java.util.List;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/{id}")
    public IngredientDto getIngredient(@PathVariable Long id) {
        return new IngredientDto(ingredientService.findIngredient(id));
    }

    @GetMapping("/")
    public List<IngredientDto> getIngredients() {
        return ingredientService.findAllIngredients().stream()
                .map(IngredientDto::new)
                .toList();
    }

    @PostMapping("/")
    public IngredientDto createIngredient(@RequestParam("ingredientName") String ingredientName)
    {
        return new IngredientDto(ingredientService.addIngredient(ingredientName));
    }

    @PutMapping("/{ingredientId} {dishId}")
    public IngredientDto addIngredient(@RequestParam("ingredientId") Long ingredientId,
                                    @RequestParam("dishId") Long dishId)
    {
        return new IngredientDto(ingredientService.addDishToIngredient(ingredientId, dishId));
    }

    @PatchMapping("/{id}")
    public IngredientDto updateIngredient(@PathVariable Long id,
                                       @RequestParam("ingredientName") String ingredientName)
    {
        return new IngredientDto(ingredientService.updateIngredient(id, ingredientName));
    }

    @DeleteMapping("/{id}")
    public IngredientDto deleteIngredient(@PathVariable Long id) {return new IngredientDto(ingredientService.deleteIngredient(id));}

    @DeleteMapping("")
    public void deleteIngredients() {
        ingredientService.deleteAllIngredients();
    }
}