package io.github.mnitek.servit.controller;

import io.github.mnitek.servit.data.RecipeRepository;
import io.github.mnitek.servit.model.Ingredient;
import io.github.mnitek.servit.model.Recipe;
import io.github.mnitek.servit.model.Step;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
@Controller
@RequestMapping("/recipes")
public class RecipeController {
    private RecipeRepository recipeRepo;

    public RecipeController(RecipeRepository recipeRepo) {
        this.recipeRepo = recipeRepo;
    }

    @GetMapping
    public String showAllRecipes(Model model) {
        log.info("Exposing all recipes");
        model.addAttribute("recipes", recipeRepo.findAll());
        return "recipes";
    }

    @GetMapping("/{id}")
    public String getSingleRecipe(@PathVariable("id") int id, Model model) {
        Recipe recipe = recipeRepo.findById(id).orElse(null);
        log.info("Exposing recipe: " + recipe);
        model.addAttribute("recipe", recipe);
        return "singleRecipe";
    }

    @GetMapping("/add")
    public String showAddRecipeForm(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "newRecipeForm";
    }

    @PostMapping
    public String addNewRecipe(@Valid Recipe recipe, Errors errors) {
        if (errors.hasErrors()) return "newRecipeForm";
        log.info("Added new recipe " + recipe);
        recipeRepo.save(recipe);
        return "redirect:/";
    }

    @PostMapping(params = "addStep")
    public String addRecipeStep(@ModelAttribute("recipe") Recipe currentRecipe) {
        currentRecipe.getSteps().add(new Step());
        return "/newRecipeForm";
    }

    @PostMapping(params = "addIngredient")
    public String addRecipeIngredient(@ModelAttribute("recipe") Recipe currentRecipe) {
        currentRecipe.getIngredients().add(new Ingredient());
        return "newRecipeForm";
    }

    @Transactional
    @PatchMapping("/{id}")
    public void togglePlanned(@PathVariable("id") int id) {
        if (!recipeRepo.existsById(id)) log.warn("Recipe does not exists");
        recipeRepo.findById(id).ifPresent(recipe -> recipe.setPlanned(!recipe.isPlanned()));
    }
}
