package com.ulstu.SushiBar.Controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ulstu.SushiBar.Models.Ingredient;
import com.ulstu.SushiBar.Services.IngredientService;
import java.util.List;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/{id}")
    public Ingredient getIngredient(@PathVariable Long id) {
        return ingredientService.findIngredient(id);
    }

    @GetMapping("/")
    public List<Ingredient> getIngredients() {
        return ingredientService.findAllIngredients();
    }

    @PostMapping("/")
    public Ingredient createIngredient(@RequestParam("ingredientName") String ingredientName)
    {
        return ingredientService.addIngredient(ingredientName);
    }

    @PatchMapping("/{id}")
    public Ingredient updateIngredient(@PathVariable Long id,
                                       @RequestParam("ingredientName") String ingredientName)
    {
        return ingredientService.updateIngredient(id, ingredientName);
    }

    @DeleteMapping("/{id}")
    public Ingredient deleteIngredient(@PathVariable Long id) {
        return ingredientService.deleteIngredient(id);
    }
}