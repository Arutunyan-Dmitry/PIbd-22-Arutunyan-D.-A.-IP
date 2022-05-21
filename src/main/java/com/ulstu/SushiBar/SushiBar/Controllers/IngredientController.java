package com.ulstu.SushiBar.SushiBar.Controllers;

import com.ulstu.SushiBar.configuration.WebConfiguration;
import org.springframework.web.bind.annotation.*;
import com.ulstu.SushiBar.SushiBar.Models.Ingredient;
import com.ulstu.SushiBar.SushiBar.Services.IngredientService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(WebConfiguration.REST_API + "/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/{id}")
    public IngredientDto getIngredient(@PathVariable Long id) {return new IngredientDto(ingredientService.findIngredient(id));}

    @GetMapping("/")
    public List<IngredientDto> getIngredients()
    {
        return ingredientService.findAllIngredients().stream()
                .map(IngredientDto::new)
                .toList();
    }

    @PostMapping("/")
    public IngredientDto createIngredient(@RequestBody @Valid IngredientDto ingredientDto)
    {
        return new IngredientDto(ingredientService.addIngredient(ingredientDto.getName(), ingredientDto.getGrm()));
    }

    @PutMapping("/{ingredientId} {dishId}")
    public IngredientDto addIngredient(@RequestBody @Valid IngredientDto ingredientDto,
                                    @RequestBody @Valid DishDto dishDto)
    {
        return new IngredientDto(ingredientService.addDishToIngredient(ingredientDto.getId(), dishDto.getId()));
    }

    @PatchMapping("/{id}")
    public IngredientDto updateIngredient(@PathVariable Long id,
                                       @RequestBody @Valid IngredientDto ingredientDto)
    {
        return new IngredientDto(ingredientService.updateIngredient(id, ingredientDto.getName(), ingredientDto.getGrm()));
    }

    @DeleteMapping("/{id}")
    public IngredientDto deleteIngredient(@PathVariable Long id) {return new IngredientDto(ingredientService.deleteIngredient(id));}

    @DeleteMapping("")
    public void deleteIngredients() {
        ingredientService.deleteAllIngredients();
    }
}