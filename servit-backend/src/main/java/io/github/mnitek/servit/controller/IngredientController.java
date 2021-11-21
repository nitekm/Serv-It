package io.github.mnitek.servit.controller;

import io.github.mnitek.servit.logic.IngredientService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {
    private IngredientService service;

    IngredientController(final IngredientService service) {
        this.service = service;
    }

    @Transactional
    @PostMapping("/toList")
    public String sendTasks(Model model) {
        model.addAttribute("msg", "Lista zakupów utworzona!");
        service.createIngredientsTasks();
        return "redirect:/recipes";
    }
    @GetMapping
    public String getAllPlannedIngredients(Model model) {
        model.addAttribute("ingredients", service.getAllPlannedIngredients());
        return "ingredients";
    }
}

