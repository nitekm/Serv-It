package io.github.mnitek.servit.controller;

import io.github.mnitek.servit.logic.IngredientService;
import io.github.mnitek.servit.model.Ingredient;
import io.github.mnitek.servit.model.Task;
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

    IngredientController(final IngredientService service) {
        this.service = service;
    }

    //    @PostMapping
//    public Mono<Ingredient> createIngredientTask(@RequestBody Ingredient ingredient) {
//        return service.createIngredientTask(ingredient);
//    }

    @GetMapping("/tasks")
    public Flux<Task> getTasks() {
        return service.getAllTasks();
    }

}

