package io.github.mnitek.servit.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="recipes")
@Setter
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Pole nazwa nie może być puste")
    private String name;
    @Pattern(regexp = "^[0-9]:[0-5][0-9]$", message = "Czas przygotowania musi być w formacie H:mm")
    private String timeToPrepare;
    @Valid
    @OneToMany(cascade = CascadeType.ALL)
    private List<Ingredient> ingredients = new ArrayList<>();
    @Valid
    @OneToMany(cascade = CascadeType.ALL)
    private List<Step> steps = new ArrayList<>();
    private boolean planned;
    private LocalDateTime createdAt;

    public Recipe() {
        steps.add(new Step());
        ingredients.add(new Ingredient());
    }

    public Recipe(String name) {
        this.name = name;
    }

    public void toggleIngredients() {
        ingredients.forEach(ingredient -> ingredient.setPlanned(!isPlanned()));
    }

    public void updateRecipe(Recipe update) {
        name = update.name;
        timeToPrepare = update.timeToPrepare;
        ingredients = update.ingredients;
        steps = update.steps;
        //planned = update.planned;
    }

    @PrePersist
    public void setCreatedAt() {
        createdAt = LocalDateTime.now();
    }
}