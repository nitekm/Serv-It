package io.github.mnitek.servit.controller;

import io.github.mnitek.servit.data.RecipeRepository;
import io.github.mnitek.servit.logic.RecipeService;
import io.github.mnitek.servit.model.Recipe;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/recipes")
@AllArgsConstructor
public class RecipeRestController {
    private RecipeService recipeService;
    private RecipeRepository recipeRepository;

    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        return ResponseEntity.ok(recipeRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
        var newRecipe = recipeRepository.save(recipe);
        return ResponseEntity.ok(newRecipe);
    }

    @PutMapping
    public ResponseEntity<Recipe> editRecipe(@PathVariable("id") int id, @Valid Recipe toUpdate) {
        if (!recipeRepository.existsById(id)) {
            throw new IllegalArgumentException("Recipe with given id not found!");
        }
        recipeRepository.findById(id).ifPresent(recipe -> {
            recipe.updateRecipe(toUpdate);
            recipeRepository.save(recipe);
        });
        return ResponseEntity.ok(toUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable("id") int id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> togglePlanned(@PathVariable("id") int id) {
        recipeService.togglePlanned(id);
        return ResponseEntity.ok().build();
    }
}
