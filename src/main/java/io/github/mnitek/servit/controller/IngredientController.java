package io.github.mnitek.servit.controller;

import io.github.mnitek.servit.logic.IngredientService;
import io.github.mnitek.servit.model.Ingredient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {
    private IngredientService service;

    @GetMapping
    public Flux<Ingredient> getAllIngredients () {
        return service.getAllIngredients();
    }

    @PostMapping
    public Mono<Ingredient> createIngredientsTasks(@RequestBody Ingredient ingredient) {
        return service.createIngredientsTasks(ingredient);
    }


}
