package io.github.mnitek.servit.controller;

import io.github.mnitek.servit.data.RecipeRepository;
import io.github.mnitek.servit.logic.RecipeService;
import io.github.mnitek.servit.model.Ingredient;
import io.github.mnitek.servit.model.Recipe;
import io.github.mnitek.servit.model.Step;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Slf4j
@Controller
@RequestMapping("/recipes")
public class RecipeController {
    private RecipeRepository recipeRepo;
    private RecipeService recipeService;

    public RecipeController(RecipeRepository recipeRepo, RecipeService recipeService) {
        this.recipeRepo = recipeRepo;
        this.recipeService = recipeService;
    }

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
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
        log.info("Adding new recipe: " + recipe);
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

    @GetMapping("/plan/{id}")
    public String togglePlanned(@PathVariable("id") int id) {
        recipeService.togglePlanned(id);
        return "redirect:/recipes";
    }

    @GetMapping("delete/{id}")
        public String deleteRecipe(@PathVariable("id") int id) {
        recipeService.deleteRecipe(id);
        return "redirect:/recipes";
    }

    @GetMapping("/edit/{id}")
    public String showEditRecipeForm(@PathVariable("id") int id, Model model) {
        Recipe recipe = recipeRepo.findById(id).orElse(null);
        model.addAttribute("recipe", recipe);
        return "editRecipeForm";
    }

    @PostMapping("/edit/{id}")
    public String editRecipe(@PathVariable("id") int id, Recipe recipe) {
        recipeRepo.save(recipe);
        return "redirect:/recipes";
    }

    /*
    * JSON responses for POSTMAN testing
    */
    @ResponseBody
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Recipe> getAllRecipes() {
        return recipeRepo.findAll();
    }
}
