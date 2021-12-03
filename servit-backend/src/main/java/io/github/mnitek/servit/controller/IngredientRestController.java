package io.github.mnitek.servit.controller;

import io.github.mnitek.servit.logic.IngredientService;
import io.github.mnitek.servit.model.Ingredient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/ingredients")
@CrossOrigin("*")
@RestController
@AllArgsConstructor
public class IngredientRestController {
    private IngredientService ingredientService;

    @GetMapping("/planned")
    public ResponseEntity<List<Ingredient>> getAllPlannedIngredients() {
        return ResponseEntity.ok(ingredientService.getAllPlannedIngredients());
    }

    @Transactional
    @PostMapping("/toList")
    public ResponseEntity<Void> createAndSendTasks() {
        ingredientService.createIngredientsTasks();
        return ResponseEntity.ok().build();
    }

    @PatchMapping("planned/{id}")
    public ResponseEntity<Void> deleteIngredientFromPlanned(@PathVariable("id") int id) {
        ingredientService.togglePlanned(id);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<?> handleIllegalArgument(IllegalArgumentException e) {
        return ResponseEntity.notFound().build();
    }
}
