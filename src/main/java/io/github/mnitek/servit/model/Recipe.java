package io.github.mnitek.servit.model;


import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    //test
    public Recipe(String name) {
        this.name = name;
    }

    @PrePersist
    public void setCreatedAt() {
        createdAt = LocalDateTime.now();
    }

    public void toggleIngredients() {
        for (Ingredient i:ingredients) {
            i.setPlanned(!i.isPlanned());
        }
    }
}