package io.github.mnitek.servit.controller;

import io.github.mnitek.servit.model.Ingredient;
import io.github.mnitek.servit.model.Recipe;
import io.github.mnitek.servit.model.Step;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.AutoPopulatingList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/recipes")
public class RecipeController {

    @GetMapping
    public String showAllRecipes(Model model) {
        log.info("Exposing all recipes");
        model.addAttribute("message", "Gdzie moje recipy?!");
        model.addAttribute("recipe", new Recipe());
        return "recipes";
    }

    @GetMapping("/add")
    public String showAddRecipeForm(Model model) {
        //model.addAttribute("steps", new Step());
        //model.addAttribute("ingredients", new Ingredient());
        model.addAttribute("recipe", new Recipe());
        return "newRecipeForm";
    }

    @PostMapping
    public String addNewRecipe(Recipe recipe) {
        log.info("Added new recipe");
        return "redirect:/";
    }
}
