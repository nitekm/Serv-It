package io.github.mnitek.servit.controller;

import io.github.mnitek.servit.logic.IngredientService;
import io.github.mnitek.servit.model.Ingredient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
