package com.ulstu.SushiBar.SushiBar.Controllers;

import com.ulstu.SushiBar.SushiBar.Services.DishService;
import com.ulstu.SushiBar.SushiBar.Services.IngredientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
@RequestMapping("/ingredient")
public class IngredientMvcController {
    private final IngredientService ingredientService;
    private final DishService dishService;

    public IngredientMvcController(IngredientService ingredientService, DishService dishService) {
        this.ingredientService = ingredientService;
        this.dishService = dishService;
    }

    @GetMapping
    public String getIngredients(Model model) {
        model.addAttribute("ingredients",
                ingredientService.findAllIngredients().stream()
                        .map(IngredientDto::new)
                        .toList());
        return "ingredient";
    }
    @GetMapping(value = {"/edit", "/edit/{id}"})
    public String editIngredient(@PathVariable(required = false) Long id, Model model) {
        if (id == null || id <= 0) {
            model.addAttribute("ingredientDto", new IngredientDto());
        } else {
            model.addAttribute("ingredientId", id);
            model.addAttribute("ingredientDto", new IngredientDto(ingredientService.findIngredient(id)));
        }
        return "ingredient-edit";
    }

    @PostMapping(value = {"", "/{id}"})
    public String saveIngredient(@PathVariable(required = false) Long id,
                              @ModelAttribute @Valid IngredientDto ingredientDto,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "ingredient-edit";
        }
        if (id == null || id <= 0) {
            ingredientService.addIngredient(ingredientDto.getName(), ingredientDto.getGrm());
        } else {
            ingredientService.updateIngredient(id, ingredientDto.getName(), ingredientDto.getGrm());
        }
        return "redirect:/ingredient";
    }

    @PostMapping("/delete/{id}")
    public String deleteIngredient(@PathVariable Long id) {
        ingredientService.deleteIngredient(id);
        return "redirect:/ingredient";
    }

    @GetMapping("/add")
    public String getForAdding(Model model) {
        model.addAttribute("ingredients",
                ingredientService.findAllIngredients().stream()
                        .map(IngredientDto::new)
                        .toList());
        model.addAttribute("dishes",
                dishService.findAllDishes().stream()
                        .map(DishDto::new)
                        .toList());
        model.addAttribute("dishIngredientDto", new DishIngredientDto());
        return "ingredient-add";
    }

    @PostMapping("/add")
    public String addIngredientToDish( @ModelAttribute @Valid DishIngredientDto dishIngredientDto,
                                       BindingResult bindingResult,
                                       Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "ingredient-add";
        }
        model.addAttribute("dishIngredientDto", new DishIngredientDto());
        ingredientService.addDishToIngredient(dishIngredientDto.getIngredientId(), dishIngredientDto.getDishId());
        return "redirect:/ingredient/add";
    }

    @GetMapping("/rem")
    public String getForRemoving(Model model) {
        model.addAttribute("ingredients",
                ingredientService.findAllIngredients().stream()
                        .map(IngredientDto::new)
                        .toList());
        model.addAttribute("dishes",
                dishService.findAllDishes().stream()
                        .map(DishDto::new)
                        .toList());
        model.addAttribute("dishIngredientDto", new DishIngredientDto());
        return "ingredient-rem";
    }

    @PostMapping("/rem")
    public String removeIngredientFromDish( @ModelAttribute @Valid DishIngredientDto dishIngredientDto,
                                            BindingResult bindingResult,
                                            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "ingredient-rem";
        }
        model.addAttribute("dishIngredientDto", new DishIngredientDto());
        ingredientService.removeIngredientFromDish(dishIngredientDto.getIngredientId(), dishIngredientDto.getDishId());
        return "redirect:/ingredient/rem";
    }
}
