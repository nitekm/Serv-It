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

    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }

    @GetMapping("/planned")
    public ResponseEntity<List<Recipe>> getAllPlannedRecipes() {
        return ResponseEntity.ok(recipeService.getAllPlanned());
    }

    @PostMapping
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
        var newRecipe = recipeService.addNewRecipe(recipe);
        return ResponseEntity.ok(newRecipe);
    }

    @PutMapping
    public ResponseEntity<Recipe> editRecipe(@PathVariable("id") int id, @Valid Recipe toUpdate) {
        var editRecipe = recipeService.editRecipe(id, toUpdate);
        return ResponseEntity.ok(editRecipe);
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
