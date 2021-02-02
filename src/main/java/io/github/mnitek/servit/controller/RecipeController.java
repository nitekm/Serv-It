package io.github.mnitek.servit.controller;

import io.github.mnitek.servit.model.Recipe;
import io.github.mnitek.servit.repository.RecipeRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

    private RecipeRepository recipeRepository;

    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        return ResponseEntity.ok(recipeRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable("id") int id) {
        return recipeRepository.findById(id)
                .map(recipe -> ResponseEntity.ok(recipe))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Recipe> addRecipe(Recipe newRecipe) {
        Recipe result = recipeRepository.save(newRecipe);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Recipe> deleteRecipeById(@PathVariable("id") int id) {
        if(!recipeRepository.existsById(id)) return ResponseEntity.notFound().build();
        recipeRepository.findById(id)
                .ifPresent(recipe -> recipeRepository.delete(recipe));
        return ResponseEntity.noContent().build();
    }
}
