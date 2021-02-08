package io.github.mnitek.servit.controller;

import io.github.mnitek.servit.data.RecipeRepository;
import io.github.mnitek.servit.model.Ingredient;
import io.github.mnitek.servit.model.Recipe;
import io.github.mnitek.servit.model.Step;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@Controller
@RequestMapping("/recipes")
public class RecipeController {
    private RecipeRepository recipeRepo;

    public RecipeController(RecipeRepository recipeRepo) {
        this.recipeRepo = recipeRepo;
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Recipe> getAll() {
        return recipeRepo.findAll();
    }

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String showAllRecipes(Model model) {
        log.info("Exposing all recipes");
        model.addAttribute("recipes", recipeRepo.findAll());
        return "recipes";
    }

    @GetMapping("/{id}")
    public String getSingleRecipe(@PathVariable("id") int id, Model model) {
        model.addAttribute("recipe", recipeRepo.findById(id));
        return "singleRecipe";
    }

    @GetMapping("/add")
    public String showAddRecipeForm(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "newRecipeForm";
    }

    @PostMapping
    public String addNewRecipe(Recipe recipe) {
        log.info("Added new recipe");
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
}
