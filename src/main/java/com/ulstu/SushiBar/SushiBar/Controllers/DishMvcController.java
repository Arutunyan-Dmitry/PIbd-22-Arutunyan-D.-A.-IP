package com.ulstu.SushiBar.SushiBar.Controllers;

import com.ulstu.SushiBar.SushiBar.Services.DishService;
import com.ulstu.SushiBar.SushiBar.Services.IngredientService;
import com.ulstu.SushiBar.user.models.UserRole;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
@RequestMapping("/dish")
public class DishMvcController {
    private final DishService dishService;
    private final IngredientService ingredientService;

    public DishMvcController(DishService dishService, IngredientService ingredientService)
    {
        this.ingredientService = ingredientService;
        this.dishService = dishService;
    }

    @GetMapping
    @Secured({UserRole.AsString.CHEF, UserRole.AsString.CLIENT, UserRole.AsString.ADMIN})
    public String getDishes(Model model) {
        model.addAttribute("dishes",
                dishService.findAllDishes().stream()
                        .map(DishDto::new)
                        .toList());
        return "dish";
    }

    @GetMapping(value = {"/edit", "/edit/{id}"})
    @Secured({UserRole.AsString.CHEF, UserRole.AsString.ADMIN})
    public String editDish(@PathVariable(required = false) Long id, Model model) {
        if (id == null || id <= 0) {
            model.addAttribute("dishDto", new DishDto());
        } else {
            model.addAttribute("dishId", id);
            model.addAttribute("dishDto", new DishDto(dishService.findDish(id)));
        }
        return "dish-edit";
    }

    @PostMapping(value = {"", "/{id}"})
    public String saveDish(@PathVariable(required = false) Long id,
                                 @ModelAttribute @Valid DishDto dishDto,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "dish-edit";
        }
        if (id == null || id <= 0) {
            dishService.addDish(dishDto.getName(), dishDto.getPrice());
        } else {
            dishService.updateDish(id, dishDto.getName(), dishDto.getPrice());
        }
        return "redirect:/dish";
    }

    @PostMapping("/delete/{id}")
    public String deleteDish(@PathVariable Long id) {
        dishService.deleteDish(id);
        return "redirect:/dish";
    }

    @GetMapping("/add")
    @Secured({UserRole.AsString.CHEF, UserRole.AsString.ADMIN})
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
        return "dish-add";
    }

    @PostMapping("/add")
    public String addIngredientToDish( @ModelAttribute @Valid DishIngredientDto dishIngredientDto,
                                       BindingResult bindingResult,
                                       Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "dish-add";
        }
        model.addAttribute("dishIngredientDto", new DishIngredientDto());
        dishService.addIngredientToDish(dishIngredientDto.getDishId(), dishIngredientDto.getIngredientId());
        return "redirect:/dish/add";
    }

    @GetMapping("/rem")
    @Secured({UserRole.AsString.CHEF, UserRole.AsString.ADMIN})
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
        return "dish-rem";
    }

    @PostMapping("/rem")
    public String removeIngredientFromDish( @ModelAttribute @Valid DishIngredientDto dishIngredientDto,
                                       BindingResult bindingResult,
                                       Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "dish-rem";
        }
        model.addAttribute("dishIngredientDto", new DishIngredientDto());
        dishService.removeIngredientFromDish(dishIngredientDto.getDishId(), dishIngredientDto.getIngredientId());
        return "redirect:/dish/rem";
    }
}