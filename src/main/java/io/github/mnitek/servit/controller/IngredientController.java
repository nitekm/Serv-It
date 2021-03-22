package io.github.mnitek.servit.controller;

import io.github.mnitek.servit.data.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/ingredients")
public class IngredientController {
    private IngredientRepository repository;

    IngredientController(final IngredientRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    String showAllPlannedIngredients (Model model) {
        log.info("Exposing all planned ingredients");
        model.addAttribute("ingredients", repository.findAllPlanned());
        return "ingredients";
    }
}


